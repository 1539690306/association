package com.ls.mapper;

import java.util.List;

import com.ls.pojo.User;

public interface UserMapper {

	public User findUserById(int id);

	public User findByUsername(String name);

	public List<User> searchByUsername(String name);

	public List<User> findAll();

	public void updateUser(User user);

	public void addUser(User user);

	public void deleteUserById(int id);

	public void resetUserPwd(int id);
	
	public void updateUserPwd(User user);
	
	public int countDeptUser(String dept);
	
	public List<User> findUserByDept(String dept);
	
	public void regist(User user);
	
	public int countWomanUser();
	
	public int countManUser();
	
	public int countUser();
}

