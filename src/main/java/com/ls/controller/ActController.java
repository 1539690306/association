package com.ls.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.ls.pojo.Act;
import com.ls.pojo.Actbmjl;
import com.ls.pojo.User;
import com.ls.service.ActService;

@Controller
public class ActController {
	@Autowired
	ActService service;

	@RequestMapping("/findAllAct")
	public String findAllAct(Model model) {
		List<Act> acts = service.findAllAct();
		for (Act act : acts) {
			int actId = act.getId();
			act.setCountuser(service.countActuser(actId));
		}
		model.addAttribute("acts", acts);
		return "/activity/listActivity";
	}

	@RequestMapping("/findActByTitle")
	public String findActByTitle(String actname, Model model) {
		List<Act> acts = service.findActByTitle(actname);

		for (Act act : acts) {
			int actId = act.getId();

			act.setCountuser(service.countActuser(actId));
		}
		model.addAttribute("acts", acts);
		return "/activity/listActivity";
	}

	@RequestMapping("/toAddAct")
	public String toAddAct() {

		return "/activity/toAddAct";
	}

	//发布活动
	@RequestMapping("/addAct")
	public String AddAct(Act act, Model model, HttpServletRequest request) {

		if (act.getActname().isEmpty()) {
			model.addAttribute("msg", "活动主题不能为空");
			return "/activity/toAddAct";

		} else {
			User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
			act.setCreater(user.getName());
			act.setCountuser(0);
			Date opeartime = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp createtime = Timestamp.valueOf(df.format(opeartime));
			act.setCreatetime(createtime);

			String et = request.getParameter("endtime");
			if (et.isEmpty()) {
				model.addAttribute("msg", "截止时间不能为空");
				return "forward:toAddAct";
			} else {
				et = et + "" + ":00";
				String ets = et.replaceAll("T", " ");

				Timestamp deadline = Timestamp.valueOf(ets);
				act.setDeadline(deadline);
				service.addAct(act);
				model.addAttribute("msg", "活动发布成功");
				return "forward:toAddAct";
			}

		}

	}

	// 用户报名活动
	@RequestMapping("/regAct")
	public String regAct(int id, HttpSession session, Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
		Act act1 = service.findActById(id);
		Timestamp createtime = act1.getCreatetime();
		Timestamp endtime = act1.getDeadline();

		Calendar calendar = Calendar.getInstance();
		Timestamp time = new Timestamp(calendar.getTimeInMillis());

		if (time.after(createtime) && endtime.after(time)) {
			if (user == null) {
				Act act = new Act();
				act.setId(id);
				session.setAttribute("act", act);
				return "/activity/toRegActForGuest";
			} else {
				Actbmjl bmjl = new Actbmjl();
				bmjl.setActId(id);
				bmjl.setUsername(user.getName());
				bmjl.setSex(user.getSex());
				bmjl.setPhone(user.getPhone());
				service.addActbmjl(bmjl);
				model.addAttribute("msg", "报名成功");
				return "forward:findAllAct";
			}

		} else {
			model.addAttribute("msg", "报名失败，不在报名时间内");
			return "forward:findAllAct";

		}

	}

	// 游客报名活动
	@RequestMapping("/guestRegAct")
	public String guestRegAct(HttpSession session, HttpServletRequest request, Model model) {
		Act act = (Act) session.getAttribute("act");

		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		if (name.isEmpty()) {
			model.addAttribute("msg", "姓名不能为空");
			return "/activity/toRegActForGuest";
		} else if (phone.isEmpty()) {
			model.addAttribute("msg", "联系方式不能为空");
			return "/activity/toRegActForGuest";
		}
		Actbmjl bmjl = new Actbmjl();
		bmjl.setActId(act.getId());
		bmjl.setUsername(name);
		bmjl.setSex(sex);
		bmjl.setPhone(phone);
		service.addActbmjl(bmjl);
		model.addAttribute("msg", "报名成功");
		return "forward:findAllAct";

	}

	//删除活动
	@RequestMapping("/deleteActById")
	public String deleteActById(int id, Model model) {
		service.deleteActById(id);
		model.addAttribute("msg", "删除成功");
		return "forward:findAllAct";
	}
	
	//删除活动报名记录
		@RequestMapping("/deleteActBmjlById")
		public String deleteActBmjlById(int id, Model model) {
			service.deleteActBmjlById(id);
			model.addAttribute("msg", "删除成功");
			return "forward:findAllAct";
		}

	// 查看活动的报名记录
	@RequestMapping("/findUserByActbmjl")
	public String findUserByActbmjl(int actId, Model model) {
		List<Actbmjl> actbmjls = service.findUserByActbmjl(actId);
		Act act = new Act();
		act.setId(actId);

		model.addAttribute("act", act);
		model.addAttribute("actbmjls", actbmjls);
		return "/activity/listActBmjl";
	}

	// 导出Excel表格
	@RequestMapping("/actBmjlExcel")
	public void actBmjlExcel(int actId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Actbmjl> actbmjls = service.findUserByActbmjl(actId);
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("活动报名记录表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("ID");
		cell = row.createCell(1);
		cell.setCellValue("活动Id");
		cell = row.createCell(2);
		cell.setCellValue("姓名");
		cell = row.createCell(3);
		cell.setCellValue("性别");
		cell = row.createCell(4);
		cell.setCellValue("联系电话");

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到
		for (int i = 0; i < actbmjls.size(); i++) {
			row = sheet.createRow(i + 1);
			Actbmjl actbmjl = actbmjls.get(i);
			row.createCell(0).setCellValue(actbmjl.getId());
			row.createCell(1).setCellValue(actbmjl.getActId());
			row.createCell(2).setCellValue(actbmjl.getUsername());
			row.createCell(3).setCellValue(actbmjl.getSex());
			row.createCell(4).setCellValue(actbmjl.getPhone());

		}
		// 第六步，下载excel
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			String fileName = "活动报名记录表.xls";
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
