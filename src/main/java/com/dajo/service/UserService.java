package com.dajo.service;

import java.util.List;

import com.dajo.model.User;



public interface UserService {
	
	User findById(int id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(int id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();

	User findByIdent(int id);
	
	public boolean isUserExist(User user);
	
}
