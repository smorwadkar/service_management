package com.service.manager.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.persistence.RequestDetails;
import com.service.manager.user.persistence.mapper.RequestDetailsMapper;
import com.service.manager.user.repository.RequestManagementRepository;

@Repository
public class RequestManagementRepositoryImpl implements RequestManagementRepository{

	@Autowired
	RequestDetailsMapper requestDetailsMapper;
	
	@Override
	public Integer insertRequest(RequestDetails requestDetails) {
		return requestDetailsMapper.insert(requestDetails);
	}

	@Override
	public RequestDetails getRequestDetailsByCriteria(Integer requestId) {
		return requestDetailsMapper.selectByPrimaryKey(requestId);
	}

}
