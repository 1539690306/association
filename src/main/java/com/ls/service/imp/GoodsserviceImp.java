package com.ls.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.dao.GoodsDao;
import com.ls.mapper.GoodsMapper;

import com.ls.pojo.Goods;

import com.ls.service.GoodsService;


@Service
public class GoodsserviceImp implements GoodsService{
       @Autowired
       GoodsMapper mapper;
       @Autowired
       GoodsDao dao;

	public void addGoods(Goods goods) {
		mapper.addGoods(goods);		
	}

	public List<Goods> findAllGoods() {
		
		return dao.selectList(null);
	}

	public List<Goods> findGoodsByName(String name) {
		
		return mapper.findGoodsByName(name);
	}

	public void deleteGoods(int id) {
		dao.deleteById(id);
	}

	public Goods findGoodsById(int id) {
		
		return dao.selectById(id);
	}

	public void updateGoods(Goods goods) {
		dao.updateById(goods);
		
	}

	

}
