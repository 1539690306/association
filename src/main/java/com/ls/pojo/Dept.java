package com.ls.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@TableName("t_dept")
public class Dept implements Serializable{
   @TableId(type =IdType.AUTO )
	private Integer id;
	
	private String deptname;

	private String introduce;
	
	private int num;
}
