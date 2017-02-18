package com.service.manager.user.service;

import java.util.List;

import com.service.manager.user.dto.ComplaintsDetails;
import com.service.manager.user.persistence.Complaints;

public interface ComplaintsManagementService {
	public int insertComplaint(ComplaintsDetails complaintsDetails);
	
	public List<Complaints> selectComplaintByCriteria(ComplaintsDetails complaintsDetails);
}
