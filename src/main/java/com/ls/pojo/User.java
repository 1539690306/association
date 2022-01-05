package com.ls.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName("t_user")
public class User implements Serializable{
	 @TableId(type =IdType.AUTO )
	private Integer id;
	private String name;
	private String password;
	private String perms;
	private String sex;
	private String dept;
	private String phone;
	private String email;
	private String major;
	private String roles;
}
