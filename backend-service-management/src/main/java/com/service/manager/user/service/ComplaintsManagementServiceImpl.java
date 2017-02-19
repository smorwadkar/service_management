package com.service.manager.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.manager.user.dto.ComplaintsDetails;
import com.service.manager.user.persistence.Complaints;
import com.service.manager.user.repository.ComplaintsRepository;

@Service
public class ComplaintsManagementServiceImpl implements
		ComplaintsManagementService {

	@Autowired
	ComplaintsRepository complaintsRepository;
	
	@Override
	public int insertComplaint(ComplaintsDetails complaintsDetails) {
		return complaintsRepository.insertComplaint(populateComplaint(complaintsDetails));
	}
	
	private Complaints populateComplaint(ComplaintsDetails complaintsDetails) {
		Complaints complaints = new Complaints();
		complaints.setComments(complaintsDetails.getComments());
		complaints.setComplaintStatus(complaintsDetails.getComplaintStatus());
		complaints.setCreateDate(complaintsDetails.getCreationTime());
		complaints.setLastUpdateDate(complaintsDetails.getLastUpdateTime());

		return complaints;
	}

	@Override
	public List<Complaints> selectComplaintByCriteria(ComplaintsDetails complaintsDetails) {
		return complaintsRepository.selectComplaintByCriteria(complaintsDetails);
	}
}
