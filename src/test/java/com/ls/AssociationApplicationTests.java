package com.ls;

import java.util.List;

import org.apache.tomcat.jni.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ls.dao.UserDao;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AssociationApplicationTests {
	
	@Autowired
	UserDao dao;
	
	@Test
	public void test1() {
		int id = 1;
		com.ls.pojo.User users = dao.selectById(id);
		System.out.println(users+"@@@@@@@@@@@@@@@@");
	}
	
}
