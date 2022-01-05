package com.ls.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@TableName("t_notice")
public class Notice implements Serializable{
   @TableId(type =IdType.AUTO )
	private Integer id;
   
	private String article;
	
	private String creater;
	
	private Timestamp createtime;
	
	private Integer countCheck;
	
}
