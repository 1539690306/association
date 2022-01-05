package com.ls.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.dao.DeptDao;
import com.ls.mapper.DeptMapper;
import com.ls.mapper.UserMapper;
import com.ls.pojo.Dept;
import com.ls.pojo.User;
import com.ls.service.DeptService;


@Service
public class DeptserviceImp implements DeptService{
       @Autowired
       DeptMapper mapper;
       @Autowired
       DeptDao dao;
       @Autowired
       UserMapper usermapper;
      
	
	public List<Dept> findAllDepts() {
		List<Dept> depts=dao.selectList(null);
		return depts;
	}

	
	public void updateDept(Dept dept) {
		dao.updateById(dept);
	}

	
	public void deleteDeptById(int id) {
		dao.deleteById(id);
		
	}

	
	public void addDept(Dept dept) {
		dao.insert(dept);
		
	}

	public Dept findDeptById(int id) {
		Dept dept=dao.selectById(id);
		return dept;
	}


	public int countDeptUser(String dept) {

		return usermapper.countDeptUser(dept);
	}


	public List<Dept> findDeptByName(String deptname) {
		
		return mapper.findDeptByName(deptname);
	}


	

}
