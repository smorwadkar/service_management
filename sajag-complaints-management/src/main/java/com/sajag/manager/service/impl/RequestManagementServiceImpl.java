package com.sajag.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sajag.manager.service.ComplaintsManagementService;
import com.sajag.manager.service.RequestManagementService;
import com.sajag.manager.service.UserManagementService;

@Service
public class RequestManagementServiceImpl implements RequestManagementService {

	@Autowired
	UserManagementService userManagementService;
	
	@Autowired
	ComplaintsManagementService complaintsManagementService;

	@Override
	public void createRequest() {
			
	}
	
}
