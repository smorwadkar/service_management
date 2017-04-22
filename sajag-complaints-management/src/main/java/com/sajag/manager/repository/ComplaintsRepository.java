package com.sajag.manager.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sajag.manager.dto.ComplaintsDetails;
import com.sajag.manager.dto.FullComplaintDetails;
import com.sajag.manager.persistence.Complaints;
import com.sajag.manager.persistence.Images;



public interface ComplaintsRepository {
	 public int insertComplaint(Complaints complaints);
	 
	 List<Complaints> selectComplaintByCriteria(ComplaintsDetails complaintsDetails);
	 
	 public int mapImagesWithComplaint(List<MultipartFile> images,Integer complaintId);
	 
	 public List<FullComplaintDetails> getAllComplaints();
	 
	 public List<FullComplaintDetails> getAllComplaints(Long userMobileNo);
	 
	 public List<Images> getComplaintImages(Integer complaintId);
	 
	 public ComplaintsDetails fetchComplaintDetails(Integer complaintId);
	 
	 public int updateComplaint(Integer complaintId,String status);
}
