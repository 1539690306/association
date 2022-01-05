package com.ls.service;

import java.util.List;

import com.ls.pojo.User;

public interface UserService {

	public User findUserById(int id);

	public User findByUsername(String name);
	
	public List<User> searchByUsername(String name);

	public List<User> findUsers();

	public User updateUser(User user);

	public void deleteUserById(int id);

	public void addUser(User user);
	
	public void resetUserPwd(int id);

	public void updateUserPwd(User user);
	
	public List<User> findUserByDept(String dept);
	
	public void regist(User user);
	
	public int countWomanUser();
	
	public int countManUser();
	
	public int countUser();
	
}
