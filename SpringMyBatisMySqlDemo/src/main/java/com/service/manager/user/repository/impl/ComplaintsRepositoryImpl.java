package com.service.manager.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.persistence.Complaints;
import com.service.manager.user.persistence.mapper.ComplaintsMapper;
import com.service.manager.user.repository.ComplaintsRepository;


@Repository
public class ComplaintsRepositoryImpl implements ComplaintsRepository{

	@Autowired
	ComplaintsMapper complaintsMapper;

	@Override
	public int insertComplaint(Complaints complaints) {
		return complaintsMapper.insert(complaints);
	}
	
}
