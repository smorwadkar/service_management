package com.sajag.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sajag.manager.dto.UserDetails;
import com.sajag.manager.exception.UserManagementException;
import com.sajag.manager.persistence.User;
import com.sajag.manager.repository.UserRepository;
import com.sajag.manager.service.UserManagementService;

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
		user.setMobileNo(Long.parseLong(userDetails.getMobileNo()));
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmailId(userDetails.getEmailId());
		user.setBloodGroup(userDetails.getBloodGroup());
		user.setCity(userDetails.getCity());
		user.setPincode(userDetails.getPincode());
		
		return user;
	}
	
	private UserDetails populateUserDetailsFromUser(User user){
		
		UserDetails userDetails = new UserDetails();
		userDetails.setMobileNo(String.valueOf(user.getMobileNo()));
		userDetails.setFirstName(user.getFirstName());
		userDetails.setLastName(user.getLastName());
		userDetails.setEmailId(user.getEmailId());
		userDetails.setBloodGroup(user.getBloodGroup());
		userDetails.setCity(user.getCity());
		userDetails.setPincode(user.getPincode());
		
		return userDetails;
	}

	@Override
	public UserDetails authenticateUser(Long userMobileNo) throws UserManagementException {
		User authenticatedUser = userRepository.authenticateUser(userMobileNo);
		return populateUserDetailsFromUser(authenticatedUser);
	}

	@Override
	public List<User> selectUserByMobileNo(Long mobileNo) {
		return userRepository.selectUserByMobileNo(mobileNo);
	}

	@Override
	public void addComplaintForUser() {
		// TODO Auto-generated method stub
		
	}
	
}
