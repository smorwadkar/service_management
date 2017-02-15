package com.service.manager.user.service;

import com.service.manager.user.dto.UserDetails;
import com.service.manager.user.exception.UserManagementException;
import com.service.manager.user.persistence.User;

public interface UserManagementService {
	public int insertUser(UserDetails userDetails) throws UserManagementException;
	
	public User authenticateUser(Long userMobileNo,CharSequence password) throws UserManagementException;
	
	public boolean checkUserName(String userName);
}
