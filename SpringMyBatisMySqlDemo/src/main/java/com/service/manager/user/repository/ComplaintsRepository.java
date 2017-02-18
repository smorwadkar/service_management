package com.service.manager.user.repository;

import java.util.List;

import com.service.manager.user.dto.ComplaintsDetails;
import com.service.manager.user.persistence.Complaints;



public interface ComplaintsRepository {
	 public int insertComplaint(Complaints complaints);
	 
	 List<Complaints> selectComplaintByCriteria(ComplaintsDetails complaintsDetails);
}
