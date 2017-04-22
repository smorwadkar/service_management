package com.sajag.manager.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sajag.manager.dto.ComplaintsDetails;
import com.sajag.manager.dto.FullComplaintDetails;
import com.sajag.manager.persistence.Complaints;

public interface ComplaintsManagementService {
	public int addComplaint(ComplaintsDetails complaintsDetails,Long userMobileNo);
	
	public List<Complaints> selectComplaintByCriteria(ComplaintsDetails complaintsDetails);
	
	public int mapImagesWithComplaint(List<MultipartFile> images);
	
	public List<FullComplaintDetails> getAllComplaints();
	
	public List<FullComplaintDetails> getAllUserComplaints(Long userMobileNo);
	
	public List<byte [] > fetchComplaintImages(Integer complaintId);
	
	public ComplaintsDetails fetchComplaintDetails(Integer complaintId);
	
	public int updateComplaint(Integer complaintId, String status);
}
