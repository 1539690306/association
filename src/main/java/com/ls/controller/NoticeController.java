package com.ls.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ls.dao.NoticeDao;
import com.ls.pojo.Check;
import com.ls.pojo.Dept;
import com.ls.pojo.MinGanCi;
import com.ls.pojo.Notice;
import com.ls.pojo.User;
import com.ls.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	NoticeService service;

	@RequestMapping("/findAllNotice")
	public String findAllNotice(Model model) {
		List<Notice> notices = service.findAllNotice();
		for (Notice notice : notices) {
			notice.setCountCheck(service.countCheck(notice.getId()));
		}
		model.addAttribute("notices", notices);
		return "/notice/listNotice";
	}

	@RequestMapping("/searchNotice")
	public String searchNotice(String article, Model model) {
		List<Notice> notices = service.searchNotice(article);

		model.addAttribute("notices", notices);
		return "/notice/listNotice";
	}

	// 删除公告
	@RequestMapping("/deleteNotice")
	public String deleteNotice(int id) {
		Notice notice = service.selectNoticeById(id);
		// 同时删除已读记录
		service.deleteCheck(notice.getId());
		service.deleteNoticeById(id);
		return "forward:/findAllNotice";
	}

	// 查收公告
	@RequestMapping("/checkNotice")
	public String checkNotice(Check check, int id, Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
		int userId = user.getId();
		int noticeId = id;
		check.setNoticeId(noticeId);
		check.setUserId(userId);
		// 一个用户对一个公告只能查收一次
		if (service.findCheck(check) == null) {
			service.addCheck(check);
			return "forward:/findAllNotice";
		} else {

			List<User> users = service.listChecker(noticeId);

			model.addAttribute("users", users);
			return "notice/listChecker";
		}

	}

	// 发布公告
	@RequestMapping("/toAddNotice")
	public String toAddNotice(Model model) {
		List<MinGanCi> words = service.selectMinGanCi();
		model.addAttribute("words", words);
		return "notice/toAddNotice";
	}

	@RequestMapping("/addNotice")
	public String addNotice(Notice notice, Model model) {
		if (notice.getArticle().isEmpty()) {
			model.addAttribute("msg", "公告内容不能为空");
			return "notice/toAddNotice";
		} else {
			User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
			notice.setCreater(user.getName());
			notice.setCountCheck(0);
			Date opeartime = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp createtime = Timestamp.valueOf(df.format(opeartime));
			notice.setCreatetime(createtime);
			service.insertNotice(notice);
			model.addAttribute("msg", "发布成功");
			return "notice/toAddNotice";
		}

	}

	@RequestMapping("/selectMinGanCi")
	public String selectMinGanCi(Model model) {
		List<MinGanCi> words = service.selectMinGanCi();
		model.addAttribute("words", words);
		return "/notice/listMinGanCi";
	}

	// 添加敏感词
	@RequestMapping("/toAddMinGanCi")
	public String toAddMinGanCi(MinGanCi word, Model model) {

		return "notice/toAddMinGanCi";

	}

	@RequestMapping("/addMinGanCi")
	public String AddMinGanCi(MinGanCi word, Model model) {
		if (word.getWord().isEmpty()) {
			model.addAttribute("msg", "敏感词不能为空");
			return "notice/toAddMinGanCi";
		} else {
			service.addMinGanCi(word);
			model.addAttribute("msg", "添加成功");
			return "notice/toAddMinGanCi";
		}
	}
	
	// 删除敏感词
		@RequestMapping("/deleteMinGanCi")
		public String deleteMinGanCi(int id,Model model) {
			service.deleteMinGanCi(id);
			model.addAttribute("msg","删除成功");
			return "forward:/selectMinGanCi";
		}
}
