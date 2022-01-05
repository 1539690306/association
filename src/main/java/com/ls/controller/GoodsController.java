package com.ls.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ls.pojo.Goods;
import com.ls.pojo.User;
import com.ls.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;

	@RequestMapping("/findAllGoods")
	public String findAllDepts(Model model) {
		List<Goods> goodss = service.findAllGoods();

		model.addAttribute("goodss", goodss);
		return "/goods/listGoods";
	}

	@RequestMapping("/findGoodsByName")
	public String findGoodsByName(String name, Model model) {
		List<Goods> goodss = service.findGoodsByName(name);

		model.addAttribute("goodss", goodss);
		return "/goods/listGoods";
	}

	// 添加物品
	@RequestMapping("/toAddGoods")
	public String addGoods() {
		return "/goods/toAddGoods";
	}

	@RequestMapping("/AddGoods")
	public String AddGoods(MultipartFile fileImage, Goods goods, Model model, HttpServletRequest request)
			throws IOException {

		System.out.println(fileImage.getOriginalFilename());

		final String imagePathRoot = "D:\\Java\\SpringBoot\\association\\src\\main\\resources\\static\\upload\\";
		File file = new File(imagePathRoot);

		if (!file.exists()) {
			file.mkdirs();
		}

		String fileName = fileImage.getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String imageFilePath = imagePathRoot + uuid + fileType;
		fileImage.transferTo(new File(imageFilePath));
		goods.setUrl(request.getContextPath() + "upload/" + uuid + fileType);
		service.addGoods(goods);
		model.addAttribute("msg", "添加成功");

		return "/goods/toAddGoods";

	}

	@RequestMapping("/deleteGoods")
	public String deleteGoods(int id) {
		service.deleteGoods(id);
		return "forward:findAllGoods";
	}

	@RequestMapping("/modifyGoods")
	public String modifyGoods(int id, Model model) {
		Goods goods = service.findGoodsById(id);
		model.addAttribute("goods", goods);
		return "/goods/modifyGoods";
	}

	@RequestMapping("/updateGoods")
	public String updateGoods(Goods goods) {

		service.updateGoods(goods);

		return "forward:findAllGoods";
	}

}
