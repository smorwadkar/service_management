package com.service.manager.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.persistence.RequestDetails;
import com.service.manager.user.persistence.mapper.RequestDetailsMapper;
import com.service.manager.user.repository.RequestsDetailsRepository;


@Repository
public class RequestDetailsRepositoryImpl implements RequestsDetailsRepository{

	@Autowired
	RequestDetailsMapper requestDetailsMapper;

	@Override
	public int insertRequest(RequestDetails requestDetails) {
		return requestDetailsMapper.insert(requestDetails);
	}
	

}
