package com.service.manager.user.repository;

import com.service.manager.user.exception.UserManagementException;
import com.service.manager.user.persistence.User;


public interface UserRepository {
	public int insertUser(User user) throws UserManagementException;
	
	public boolean checkUsername(String userName);
	
	public User authenticateUser(Long userMobileNo, CharSequence password) throws UserManagementException ;
}
