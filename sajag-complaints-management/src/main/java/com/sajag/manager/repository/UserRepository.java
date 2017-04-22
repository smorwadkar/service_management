package com.sajag.manager.repository;

import java.util.List;

import com.sajag.manager.exception.UserManagementException;
import com.sajag.manager.persistence.User;


public interface UserRepository {
	public int insertUser(User user) throws UserManagementException;
	
	public boolean checkUsername(String userName);
	
	public User authenticateUser(Long userMobileNo) throws UserManagementException ;
	
	public List<User> selectUserByMobileNo(Long mobileNo);
}
