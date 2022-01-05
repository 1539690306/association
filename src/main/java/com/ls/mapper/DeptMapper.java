package com.ls.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ls.pojo.Dept;

public interface DeptMapper {
    
	public List<Dept> findAllDepts();
	
	 public Dept findDeptById(int id);
	 
	 public void updateDept(Dept dept);
	 
	 public void deleteDeptById(int id);
	 
	 public void addDept(Dept dept);
	 
	 public List<Dept> findDeptByName(String deptname);
}
