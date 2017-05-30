package com.dajo.dao;

import java.util.List;

import com.dajo.model.User;


public interface UserDao {

	User findById(int id);
	
	User findByIdent(int ide);
	
	void save(User user);
	
	void deleteByIdent(String sso);
	void deleteById(int id);
	
	List<User> findAllUsers();

}

