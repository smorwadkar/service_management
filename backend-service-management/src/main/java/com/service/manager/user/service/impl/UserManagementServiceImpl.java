package com.service.manager.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.manager.user.dto.UserDetails;
import com.service.manager.user.exception.UserManagementException;
import com.service.manager.user.persistence.Address;
import com.service.manager.user.persistence.User;
import com.service.manager.user.repository.UserRepository;
import com.service.manager.user.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public int insertUser(UserDetails userDetails) throws UserManagementException {
		return userRepository.insertUser(populateUserFromDetails(userDetails));
	}

	@Override
	public boolean checkUserName(String userName) {
		return userRepository.checkUsername(userName);
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
		User authenticatedUser = userRepository.authenticateUser(userMobileNo, password);
		return authenticatedUser;
	}

	@Override
	public List<User> selectUserByMobileNo(Long mobileNo) {
		return userRepository.selectUserByMobileNo(mobileNo);
	}
	
}
