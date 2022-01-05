package com.ls.service;
import java.util.*;

import com.ls.pojo.Goods;

public interface GoodsService {
		 
	 public void addGoods(Goods goods);

	 public List<Goods> findAllGoods();
	 
	 public List<Goods> findGoodsByName(String name);
	 
	 public void deleteGoods(int id);
	 
	 public Goods findGoodsById(int id);
	 
	 public void updateGoods(Goods goods);
}
