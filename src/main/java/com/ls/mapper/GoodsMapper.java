package com.ls.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;


import com.ls.pojo.Goods;

public interface GoodsMapper {
    
	 public void addGoods(Goods goods);
	 
	 public List<Goods> findGoodsByName(String name);
	 
	 public void updateGoods(Goods goods);
}
