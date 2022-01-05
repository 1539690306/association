package com.ls.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ls.pojo.Actbmjl;
import com.ls.pojo.Dept;
import com.ls.pojo.User;
import com.ls.service.DeptService;
import com.ls.service.UserService;

@Controller
public class DeptController {
	@Autowired
	DeptService service;
	@Autowired
	UserService userservice;

	@RequestMapping("/findAllDepts")
	public String findAllDepts(Model model) {
		List<Dept> depts = service.findAllDepts();
		for (Dept dept : depts) {
			int num = service.countDeptUser(dept.getDeptname());
			dept.setNum(num);

		}
		model.addAttribute("depts", depts);
		return "/dept/listDept";
	}

	@RequestMapping("/findDeptByName")
	public String findDeptByName(HttpServletRequest request, Model model) {
		String deptname = request.getParameter("findDeptByName");
		List<Dept> depts = service.findDeptByName(deptname);

		for (Dept dept : depts) {
			int num = service.countDeptUser(dept.getDeptname());
			dept.setNum(num);

		}
		model.addAttribute("depts", depts);
		return "/dept/listDept";
	}

	@RequestMapping("/toAddDept")
	public String addDept() {
		return "/dept/toAddDept";
	}

	@RequestMapping("/modifyDept")
	public String findDeptById(int id, Model model) {

		Dept dept = service.findDeptById(id);
		model.addAttribute("dept", dept);

		return "dept/modifyDept";
	}

	@RequestMapping("/updateDept")
	public String updateDept(Dept dept, Model model) {
		if (dept.getDeptname().isEmpty()) {
			model.addAttribute("msg", "部门名不能为空");
			return "dept/modifyDept";
		} else {
			service.updateDept(dept);
			model.addAttribute("msg", "修改成功");
			return "dept/modifyDept";

		}

	}

	@RequestMapping("/addDept")
	public String addDept(Dept dept, Model model) {
		if (dept.getDeptname().isEmpty()) {
			model.addAttribute("msg", "部门名不能为空");
			return "dept/toAddDept";

		} else {
			service.addDept(dept);
			return "forward:findAllDepts";
		}

	}

	@RequestMapping("/deleteDept")
	public String deleteDept(int id) {
		service.deleteDeptById(id);
		return "forward:findAllDepts";
	}

	@RequestMapping("/findAllDeptsToSelect")
	public String findAllDeptsToSelect(Model model) {
		List<Dept> depts = service.findAllDepts();
		model.addAttribute("depts", depts);
		return "/user/toAddUser";
	}

	@RequestMapping("/findUserByDept")
	public String findUserByDept(String deptname, Model model) {
		List<User> users = userservice.findUserByDept(deptname);
		Dept dept = new Dept();
		dept.setDeptname(deptname);
		model.addAttribute("dept", dept);
		model.addAttribute("users", users);
		return "/dept/listDeptUser";
	}

	// 导出Excel表格
	@RequestMapping("/deptUserExcel")
	public void deptUserExcel(String deptname, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = userservice.findUserByDept(deptname);
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("用户信息表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("ID");
		cell = row.createCell(1);
		cell.setCellValue("用户名");
		cell = row.createCell(2);
		cell.setCellValue("性别");
		cell = row.createCell(3);
		cell.setCellValue("部门");
		cell = row.createCell(4);
		cell.setCellValue("职位");
		cell = row.createCell(5);
		cell.setCellValue("联系电话");
		cell = row.createCell(6);
		cell.setCellValue("邮箱");
		cell = row.createCell(7);
		cell.setCellValue("专业");
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到
		for (int i = 0; i < users.size(); i++) {
			row = sheet.createRow(i + 1);
			User user = users.get(i);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getName());
			row.createCell(2).setCellValue(user.getSex());
			row.createCell(3).setCellValue(user.getDept());
			row.createCell(4).setCellValue(user.getRoles());
			row.createCell(5).setCellValue(user.getPhone());
			row.createCell(6).setCellValue(user.getEmail());
			row.createCell(7).setCellValue(user.getMajor());
		}
		// 第六步，下载excel
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			String fileName = "用户信息表.xls";
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//图表统计
	@RequestMapping("/deptCharts")
	@ResponseBody
	public List<Dept> deptCharts(Model model) {
		List<Dept> depts = service.findAllDepts();
		for (Dept dept : depts) {
			int num = service.countDeptUser(dept.getDeptname());
			dept.setNum(num);
		}
		return depts;
	}	
	@GetMapping(value = "/deptCharts")
    public String echarts(Model model){
        System.err.println("========开始");
        return "/dept/ECharts_Dept";
    }


}
