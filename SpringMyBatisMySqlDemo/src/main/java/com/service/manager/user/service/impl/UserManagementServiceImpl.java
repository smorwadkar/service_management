package com.service.manager.user.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.manager.user.dto.UserDetails;
import com.service.manager.user.exception.UserManagementException;
import com.service.manager.user.persistence.Address;
import com.service.manager.user.persistence.User;
import com.service.manager.user.repository.impl.UserRepositoryImpl;
import com.service.manager.user.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService{
	
	@Autowired
	UserRepositoryImpl userRepositoryImpl;
	
	@Override
	public int insertUser(UserDetails userDetails) throws UserManagementException {
		return userRepositoryImpl.insertUser(populateUserFromDetails(userDetails));
	}

	@Override
	public boolean checkUserName(String userName) {
		return userRepositoryImpl.checkUsername(userName);
	}
	

	private User populateUserFromDetails(UserDetails userDetails){
		
		User user = new User();
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setMobileNo(Long.parseLong(userDetails.getMobileNo()));
		user.setEmailId(userDetails.getEmailId());
		
		Address address = new Address();
		address.setFirstLine(userDetails.getAddress());
		ArrayList<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		user.setAddresses(addresses);
		
		return user;
	}

	@Override
	public User authenticateUser(Long userMobileNo, CharSequence password) throws UserManagementException {
		User authenticatedUser = userRepositoryImpl.authenticateUser(userMobileNo, password);
		return authenticatedUser;
	}
	
}
