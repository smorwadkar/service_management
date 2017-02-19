package com.service.manager.user.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.service.manager.user.dto.ErrorMessage;
import com.service.manager.user.dto.UserDetails;
import com.service.manager.user.exception.UserManagementException;
import com.service.manager.user.persistence.User;
import com.service.manager.user.persistence.mapper.UserMapper;
import com.service.manager.user.service.impl.UserManagementServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserManagementServiceImpl userManagementServiceImpl; 
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value = "/userRegistration" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void registerUser(@RequestBody UserDetails userDetails) throws UserManagementException{
		userManagementServiceImpl.insertUser(userDetails);
	}
	
	@RequestMapping(value = "/userLogin" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User userLogin(@RequestParam("userMobileNo") Long userMobileNo,@RequestParam("password") CharSequence password) throws UserManagementException{
		User authenticatedUser = userManagementServiceImpl.authenticateUser(userMobileNo, password);
		
		return authenticatedUser;
	} 
	
	
	@ExceptionHandler(UserManagementException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleException(UserManagementException exception){
		ErrorMessage errorMessage = new ErrorMessage("error",exception.getMessage());
		return errorMessage; 
	}
}
