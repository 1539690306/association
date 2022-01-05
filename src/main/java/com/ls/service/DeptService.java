package com.ls.service;
import java.util.*;

import com.ls.pojo.Dept;
import com.ls.pojo.User;

public interface DeptService {
	
	public List<Dept> findAllDepts();
	
	 public Dept findDeptById(int id);
	 
	 public void updateDept(Dept dept);
	 
	 public void deleteDeptById(int id);
	 
	 public void addDept(Dept dept);
	 
	 public int countDeptUser(String dept);

	 public List<Dept> findDeptByName(String deptname);
}
