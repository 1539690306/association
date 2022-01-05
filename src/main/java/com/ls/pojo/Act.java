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
@TableName("t_act")
public class Act implements Serializable{
   @TableId(type =IdType.AUTO )
	private Integer id;
   
    private String creater;
    
    private String actname;
    
    private String content;
    
    private Timestamp createtime;
	
	private Timestamp deadline;
	
	private Integer countuser;
     
}
