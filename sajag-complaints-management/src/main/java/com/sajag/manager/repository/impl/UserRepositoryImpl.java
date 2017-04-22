package com.sajag.manager.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sajag.manager.exception.UserManagementException;
import com.sajag.manager.persistence.User;
import com.sajag.manager.persistence.UserExample;
import com.sajag.manager.persistence.mapper.UserMapper;
import com.sajag.manager.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	UserMapper userMapper = null;
	
	@Override
	public int insertUser(User user) throws UserManagementException {
			
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
	public User authenticateUser(Long userMobileNo) throws UserManagementException {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileNoEqualTo(userMobileNo);
		
		List<User> authenticatedUsers = userMapper.selectByExample(userExample);
		if(!authenticatedUsers.isEmpty()){
			userExample.clear();
			userExample.createCriteria().andMobileNoEqualTo(userMobileNo);
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

	@Override
	public List<User> selectUserByMobileNo(Long mobileNo) {

		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileNoEqualTo(mobileNo);

		return userMapper.selectByExample(userExample);
	}

}
