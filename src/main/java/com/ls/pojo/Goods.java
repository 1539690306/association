package com.ls.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@TableName("t_goods")
public class Goods implements Serializable{
   @TableId(type =IdType.AUTO )
	private Integer id;
	
	private String name;
	
	private double price;
	
	private String url;

	private String ms;
	
	private String state;
	
}
