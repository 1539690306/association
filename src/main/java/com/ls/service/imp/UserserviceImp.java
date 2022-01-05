package com.ls.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.mapper.UserMapper;
import com.ls.pojo.User;
import com.ls.service.UserService;

@Service
public class UserserviceImp implements UserService {
	@Autowired
	UserMapper mapper;

	public User findByUsername(String name) {

		return mapper.findByUsername(name);
	}

	public List<User> findUsers() {

		return mapper.findAll();
	}

	public User updateUser(User user) {
		mapper.updateUser(user);
		return user;
	}

	public void deleteUserById(int id) {
		mapper.deleteUserById(id);

	}

	public void addUser(User user) {
		mapper.addUser(user);

	}

	public User findUserById(int id) {
		User user = mapper.findUserById(id);
		return user;
	}

	public List<User> searchByUsername(String name) {
		List<User> users = mapper.searchByUsername(name);
		return users;
	}

	public void resetUserPwd(int id) {
		mapper.resetUserPwd(id);

	}

	public void updateUserPwd(User user) {
		mapper.updateUserPwd(user);

	}

	public List<User> findUserByDept(String dept) {

		return mapper.findUserByDept(dept);
	}

	public void regist(User user) {
		mapper.regist(user);
		
	}

	public int countWomanUser() {
		
		return mapper.countWomanUser();
	}

	public int countManUser() {
		
		return mapper.countManUser();
	}

	public int countUser() {
		// TODO Auto-generated method stub
		return mapper.countUser();
	}


}
