package com.sajag.manager.service;

import java.util.List;

import com.sajag.manager.dto.UserDetails;
import com.sajag.manager.exception.UserManagementException;
import com.sajag.manager.persistence.User;

public interface UserManagementService {
	public int insertUser(UserDetails userDetails) throws UserManagementException;
	
	public UserDetails authenticateUser(Long userMobileNo) throws UserManagementException;
	
	public boolean checkUserName(String userName);
	
	public List<User> selectUserByMobileNo(Long mobileNo);
	
	public void addComplaintForUser();
	
//	public 
}
