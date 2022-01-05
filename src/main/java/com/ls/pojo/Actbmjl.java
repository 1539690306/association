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
@TableName("t_actbmjl")
public class Actbmjl implements Serializable{
   @TableId(type =IdType.AUTO )
	private Integer id;
   
    private Integer actId;
    
    private String username;
    
    private String sex;

	private String phone;
   
}
