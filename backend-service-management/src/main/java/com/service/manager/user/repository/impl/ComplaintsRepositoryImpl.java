package com.service.manager.user.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.dto.ComplaintsDetails;
import com.service.manager.user.persistence.Complaints;
import com.service.manager.user.persistence.ComplaintsExample;
import com.service.manager.user.persistence.mapper.ComplaintsMapper;
import com.service.manager.user.repository.ComplaintsRepository;


@Repository
public class ComplaintsRepositoryImpl implements ComplaintsRepository{

	@Autowired
	ComplaintsMapper complaintsMapper;

	@Override
	public int insertComplaint(Complaints complaints) {
		complaintsMapper.insertWithKey(complaints);
		return complaints.getComplaintId();
	}

	@Override
	public List<Complaints> selectComplaintByCriteria(ComplaintsDetails complaintsDetails) {
		
		ComplaintsExample complaintsExample = new ComplaintsExample();
		complaintsExample
				.createCriteria()
				.andCommentsEqualTo(complaintsDetails.getComments())
				.andComplaintStatusEqualTo(complaintsDetails.getComplaintStatus())
				.andCreateDateEqualTo(complaintsDetails.getCreationTime())
				.andLastUpdateDateEqualTo(complaintsDetails.getLastUpdateTime());

		return complaintsMapper
				.selectByExample(complaintsExample); 
	}
	
}
