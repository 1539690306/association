package com.ls.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ls.pojo.Dept;
import com.ls.pojo.User;
import com.ls.service.DeptService;
import com.ls.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	DeptService deptservice;
	@Autowired
	JavaMailSenderImpl javaMailSender;

	// 不解释
	@RequestMapping("/findAllUser")
	public String findAllUser(Model model) {
		List<User> users = service.findUsers();
		model.addAttribute("users", users);
		return "/user/listUser";
	}

	// 按用户名模糊查询用户
	@RequestMapping("/searchByUsername")
	public String searchByUsername(String name, Model model) {
		List<User> users = service.searchByUsername(name);
		model.addAttribute("users", users);
		return "/user/listUser";
	}

	// 前往添加用户页面
	@RequestMapping("/toAddUser")
	public String add(Model model) {
		List<Dept> depts = deptservice.findAllDepts();
		model.addAttribute("depts", depts);
		return "/user/toAddUser";
	}

	// 添加用户
	@RequestMapping("/addUser")
	public String addUser(User user, Model model) {
		if (user.getRoles() == null) {
			user.setRoles("普通用户");
		}
		String name = user.getName();
		if (name.isEmpty()) {
			model.addAttribute("msg", "用户名不能为空");
			return "forward:toAddUser";
		} else if (service.findByUsername(name) == null) {
			service.addUser(user);
			model.addAttribute("msg", "添加成功");
			return "forward:toAddUser";
		} else {
			model.addAttribute("msg", "用户名已存在");
			return "forward:toAddUser";
		}

	}

	// 修改用户信息
	@RequestMapping("/modifyUser")
	public String findUserById(int id, Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
		User user1 = service.findUserById(id);
		if (user.getRoles().equals("管理员")) {
			if (user1.getRoles().equals("管理员") || user1.getRoles().equals("超级管理员")) {
				return "noAuth";
			} else {
				model.addAttribute("user", user1);
				return "user/modifyUser";
			}
		} else {
			model.addAttribute("user", user1);
			return "user/modifyUser";
		}

	}

	// 通过id查询用户信息
	@RequestMapping("/searchUserById")
	public String searchUserById(int id, Model model) {

		User user = service.findUserById(id);
		model.addAttribute("user", user);
		return "user/infomation";
	}

	// 更新用户信息
	@RequestMapping("/updateUser")
	public String updateUser(User user, Model model) {
		String editName = user.getName();
		int userid = user.getId();
		User user1 = service.findByUsername(editName);
		User user2 = service.findUserById(userid);
		if (editName.isEmpty()) {
			model.addAttribute("msg", "用户名不能为空");
			return "/user/modifyUser";
		} else if (user1 != null) {
			if (user1.getName().equals(user2.getName())) {
				service.updateUser(user);
				model.addAttribute("msg", "更新成功");
				return "/user/modifyUser";
			} else {
				model.addAttribute("msg", "用户名已存在");
				return "/user/modifyUser";
			}
		} else {
			service.updateUser(user);
			model.addAttribute("msg", "更新成功");
			return "/user/modifyUser";
		}

	}

	// 修改后执行更新个人信息
	@RequestMapping("/updateInformation")
	public String updateInformation(User user, Model model) {
		User user2 = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
		User user1 = service.findByUsername(user.getName());
		user.setRoles(user2.getRoles());
		if (user.getName().isEmpty()) {
			model.addAttribute("msg", "用户名不能为空");
			return "/user/information";
		} else if (user1 != null) {
			if (user1.getName().equals(user2.getName())) {
				service.updateUser(user);
				model.addAttribute("msg", "更新成功");
				return "/user/information";
			} else {
				model.addAttribute("msg", "用户名已存在");
				return "/user/information";
			}

		} else {
			service.updateUser(user);
			model.addAttribute("msg", "更新成功");
			return "forward:information";
		}

	}

	// 删除用户
	@RequestMapping("/deleteUser")
	public String deleteUser(int id) {
		User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
		User user1 = service.findUserById(id);
		if (user.getRoles().equals("管理员")) {
			if (user1.getRoles().equals("管理员") || user1.getRoles().equals("超级管理员")) {
				return "noAuth";
			} else {
				service.deleteUserById(id);
				return "forward:findAllUser";
			}
		} else {
			service.deleteUserById(id);
			return "forward:findAllUser";
		}

	}

	// 重置用户密码为123456
	@RequestMapping("/resetUserPwd")
	public String resetUserPwd(int id, Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
		User user1 = service.findUserById(id);
		if (user.getRoles().equals("管理员")) {
			if (user1.getRoles().equals("管理员") || user1.getRoles().equals("超级管理员")) {
				return "noAuth";
			} else {
				service.resetUserPwd(id);
				return "forward:findAllUser";
			}
		} else {
			service.resetUserPwd(id);
			return "forward:findAllUser";
		}

	}

	// 前往修改密码页面
	@RequestMapping("/toUpdateUserPwd")
	public String toUpdateUserPwd() {

		return "user/toUpdateUserPwd";
	}

	// 修改密码
	@RequestMapping("/updateUserPwd")
	public String updateUserPwd(Model model, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户

		String pwd = request.getParameter("pwd");
		String newPwd = request.getParameter("newPwd");
		String confirmPwd = request.getParameter("confirmPwd");
		if (pwd.isEmpty()) {
			model.addAttribute("msg", "旧密码不能为空");
		} else if (newPwd.isEmpty()) {
			model.addAttribute("msg", "新密码不能为空");
		} else if (confirmPwd.isEmpty()) {
			model.addAttribute("msg", "确认密码不能为空");
		} else if (user.getPassword().equals(pwd) == false) {
			model.addAttribute("msg", "旧密码错误");
		} else if ((newPwd.equals(confirmPwd)) == false) {
			model.addAttribute("msg", "两次密码不一致");
		} else {
			user.setPassword(newPwd);
			service.updateUserPwd(user);
			model.addAttribute("msg", "密码修改成功");
		}

		return "forward:toUpdateUserPwd";
	}

	// 查看修改个人资料
	@RequestMapping("/information")
	public String information(Model model, HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
		if (user == null) {
			return "redirect:tologin";
		}
		model.addAttribute("user", user);
		return "user/information";
	}

	/**
	 * 
	 * 测试thymeleaf
	 */
	@RequestMapping("/show")
	public String showIndex(Model model) {
		model.addAttribute("name", "欢迎访问");
		return "show";
	}

	// 主页
	@RequestMapping("/index")
	public String index(Model model, HttpSession session) {
		User user = (User) session.getAttribute("usersession");
		model.addAttribute("user", user);
		return "index";
	}

	// 前往登录页面
	@RequestMapping("/toLogin")
	public String login() {
		return "login";
	}

	// 登录
	@RequestMapping("/login")
	public String log(String name, String password, Model model, HttpSession session) {
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		Subject subject = SecurityUtils.getSubject();

		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			model.addAttribute("msg", "用户名不存在");
			return "forward:/toLogin";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg", "密码错误");
			return "forward:/toLogin";
		}
		User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
		session.setAttribute("usersession", user);

		return "redirect:/index";
	}

	// 注销登录
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "forward:/toLogin";
	}

	// 访问未授权页面的跳转
	@RequestMapping("/noAuth")
	public String unauthc() {

		return "noAuth";
	}

	
	

	@RequestMapping("/toRegist")
	public String register() {

		return "toRegist";
	}

	@RequestMapping("/regist")
	public String register(HttpServletRequest request, HttpSession session, Model model) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String yzm = request.getParameter("yzm");
		if (name.isEmpty()) {
			model.addAttribute("msg", "用户名不能为空");
			return "toRegist";
		} else if (password.isEmpty()) {
			model.addAttribute("msg", "密码不能为空");
			return "toRegist";
		} else if (email.isEmpty()) {
			model.addAttribute("msg", "邮箱不能为空");
			return "toRegist";
		} else if (email.isEmpty()) {
			model.addAttribute("msg", "邮箱不能为空");
			return "toRegist";
		} else {
			User user1 = service.findByUsername(name);
			if (user1 == null) {

				if (yzm.equals(session.getAttribute("emailServiceCode"))) {
					User user = new User();
					user.setName(name);
					user.setPassword(password);
					user.setEmail(email);
					service.regist(user);
					session.removeAttribute("emailServiceCode");
					return "regsuccess";
				} else {
					model.addAttribute("msg", "验证码错误");
					return "toRegist";
				}
			} else {
				model.addAttribute("msg", "用户名已存在");
				return "toRegist";
			}
		}
	}

	// 邮箱注册
	@RequestMapping("/sendEmail")
	public String sendMail(HttpServletRequest request, HttpSession session) {
		String receiver = request.getParameter("email");
		Integer n = new Random().nextInt(9999);
		String emailServiceCode = Integer.toString(n);
		SimpleMailMessage message = new SimpleMailMessage();
		// 邮件设置
		message.setSubject("注册验证码");
		message.setText("注册验证码是：" + emailServiceCode);
		message.setTo(receiver);
		message.setFrom("1539690306@qq.com");
		javaMailSender.send(message);
		session.setAttribute("emailServiceCode", emailServiceCode);
		return "toRegist";
	}

	@RequestMapping("/toForgotPwd")
	public String toForgotPwd() {

		return "toForgotPwd";
	}
	
	// 忘记密码发送邮件
	@RequestMapping("/sendEmailPwd")
	public String sendEmailPwd(HttpServletRequest request, HttpSession session, Model model) {
		String name = request.getParameter("nameforPwd");
		if (name.isEmpty()) {
			model.addAttribute("msg", "用户名不能为空");
			return "toForgotPwd";
		} else {
			User user = service.findByUsername(name);
			if (user != null) {
				String receiver = user.getEmail();
				Integer n = new Random().nextInt(9999);
				String emailServiceCodePwd = Integer.toString(n);
				SimpleMailMessage message = new SimpleMailMessage();
				// 邮件设置
				message.setSubject("重置密码验证码");
				message.setText("重置密码验证码是：" + emailServiceCodePwd);
				message.setTo(receiver);
				message.setFrom("1539690306@qq.com");
				javaMailSender.send(message);
				int uid = user.getId();
				session.setAttribute("uid", uid);
				session.setAttribute("emailServiceCodePwd", emailServiceCodePwd);
				return "toForgotPwd2";
			} else {
				model.addAttribute("msg", "用户不存在");
				return "toForgotPwd";
			}
		}
	}

	@RequestMapping("/forgotPwd")
	public String forgotPwd(HttpServletRequest request, HttpSession session, Model model) {
		String yzmforPwd = request.getParameter("yzmforPwd");
		if (yzmforPwd.equals(session.getAttribute("emailServiceCodePwd"))) {
			int id = (int) session.getAttribute("uid");
			service.resetUserPwd(id);
			return "forgotPwdsuccess";
		} else {
			model.addAttribute("msg", "验证码错误");
			return "toForgotPwd2";
		}
	}

	// 导出Excel表格
	@RequestMapping("/userExcel")
	public void userExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = service.findUsers();
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

	@RequestMapping("/sexCharts")
	public String sexCharts(HttpServletRequest request, HttpServletResponse response) {
		double sumUser = service.countUser();
		double countMan = service.countManUser();
		int perCountMan = (int) ((countMan / sumUser) * 100);
		double countWoman = service.countWomanUser();
		int perCountWoman = (int) ((countWoman / sumUser) * 100);
		int unknownSex = (int) (sumUser - countMan - countWoman);
		int perUnknownSex = (int) ((unknownSex / sumUser) * 100);

		request.setAttribute("sumUser", (int)sumUser);
		request.setAttribute("countMan", (int)countMan);
		request.setAttribute("countWoman", (int)countWoman);
		request.setAttribute("unknownSex", unknownSex);
		request.setAttribute("perCountWoman", perCountWoman);
		request.setAttribute("perCountMan", perCountMan);
		request.setAttribute("perUnknownSex", perUnknownSex);
		return "/user/highcharts_UserSex";
	}

}
