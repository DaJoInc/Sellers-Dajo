package com.dajo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dajo.dao.UserDao;
import com.dajo.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public User findByName(String name) {
		return null;
	}

	public User findByIdent(int id) {
		User user = dao.findByIdent(id);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}


	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends.
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setIdentId(user.getIdentId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			entity.setUserProducts(user.getUserProducts());
			entity.setIdentId(user.getIdentId());
		}
	}

	@Override
	public void deleteUserById(int id) {
     User entity = dao.findById(id);
     dao.deleteById(entity.getId());
	}


	public void deleteUserByIdent(String ide) {
		dao.deleteByIdent(ide);
	}

	public List<User> findAllUsers() {

		return dao.findAllUsers();
	}

	@Override
	public void deleteAllUsers() {

	}

	@Override
	public boolean isUserExist(User user) {
		return false;
	}

	public boolean isUserSSOUnique(Integer id, int ide) {
		User user = findByIdent(ide);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

}
