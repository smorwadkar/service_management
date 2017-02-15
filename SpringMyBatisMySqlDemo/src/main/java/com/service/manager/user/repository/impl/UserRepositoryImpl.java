package com.service.manager.user.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.exception.UserManagementException;
import com.service.manager.user.persistence.Address;
import com.service.manager.user.persistence.User;
import com.service.manager.user.persistence.UserExample;
import com.service.manager.user.persistence.mapper.AddressMapper;
import com.service.manager.user.persistence.mapper.UserMapper;
import com.service.manager.user.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	UserMapper userMapper = null;
	
	@Autowired
	AddressMapper addressMapper;
	
	@Override
	public int insertUser(User user) throws UserManagementException {
			
			Address address = new Address();
			address.setCity(user.getAddresses().get(0).getFirstLine());
			
			addressMapper.insert(address);
			
			/*	AddressExample addressExample = new AddressExample();
			addressExample.createCriteria().andFirstLineEqualTo(value)
			*/
			
			UserExample userExample = new UserExample();
			userExample.createCriteria().andMobileNoEqualTo(user.getMobileNo());
			
			if(!userMapper.selectByExample(userExample).isEmpty())
			{
				throw new UserManagementException("User already exists with same Mobile No.");
			}else{
				return userMapper.insert(user);
				
			}
	}

	@Override
	public boolean checkUsername(String userName) {
		return false;
			
		/*User userExisted = userMapper.selectByUsername(userName);
		if(userExisted!=null){
			return true;
		}else{
			return false;
		}*/
	}

	@Override
	public User authenticateUser(Long userMobileNo, CharSequence password) throws UserManagementException {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileNoEqualTo(userMobileNo);
		
		List<User> authenticatedUsers = userMapper.selectByExample(userExample);
		if(!authenticatedUsers.isEmpty()){
			userExample.clear();
			userExample.createCriteria().andMobileNoEqualTo(userMobileNo).andPasswordEqualTo(password.toString());
			authenticatedUsers.clear();
			authenticatedUsers = userMapper.selectByExample(userExample);
			if(!authenticatedUsers.isEmpty()){
				return authenticatedUsers.get(0);
			}else{
				throw new UserManagementException("Wrong Password");
			}
		}else{
			throw new UserManagementException("Wrong User Mobile No./Username");
		}
		
	}

}
