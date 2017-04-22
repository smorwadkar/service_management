package com.sajag.manager.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sajag.manager.dto.ComplaintsDetails;
import com.sajag.manager.dto.FullComplaintDetails;
import com.sajag.manager.persistence.Complaints;
import com.sajag.manager.persistence.mapper.ComplaintsMapper;
import com.sajag.manager.service.ComplaintsManagementService;
import com.sajag.manager.service.RequestManagementService;

@RestController
@RequestMapping("/api/request")
public class RequestManagementController {

	@Autowired
	ComplaintsMapper complaintsMapper;
	
	@Autowired
	RequestManagementService requestManagementService;
	
	@Autowired
	ComplaintsManagementService complaintsManagementService;


	@RequestMapping(value = "/createRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void createRequest(@RequestParam("mobileNo") Long userMobileNo,
			@RequestParam("comments") String comments,
			@RequestParam("complaintStatus") String complaintStatus,
			@RequestParam("department_id") Integer departmentId,
			@RequestParam("image1") MultipartFile image1,
			@RequestParam("image2") MultipartFile image2) {
	
		ComplaintsDetails complaintsDetails = new ComplaintsDetails();
		complaintsDetails.setComments(comments);
		List<MultipartFile> images = new ArrayList<>();
		images.add(image1);
		images.add(image2);
		complaintsDetails.setImages(images);
		complaintsDetails.setComplaintStatus("In Progress");
		complaintsDetails.setCreationTime(new Date());
		complaintsDetails.setLastUpdateTime(new Date());
		complaintsDetails.setDepartmentId(departmentId);
		
		complaintsManagementService.addComplaint(complaintsDetails,userMobileNo);
	}

	
	@RequestMapping(value = "/allRequests" , method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FullComplaintDetails> getAllRequests(){
		
		return complaintsManagementService.getAllComplaints();
		
	}
	
	@RequestMapping(value = "/getAllUserRequests" , method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FullComplaintDetails> getAllUserRequests(Long userMobileNo){
		
		return complaintsManagementService.getAllUserComplaints(userMobileNo);		
	}
	
	@RequestMapping(value = "/getComplaintImages" , method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<byte []> getComplaintImages(Integer complaintId){
		return complaintsManagementService.fetchComplaintImages(complaintId);
	}
	
	
	@RequestMapping(value = "/complaintDetailsById" , method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ComplaintsDetails getComplaintDetailsById(Integer complaintId){
		
		return complaintsManagementService.fetchComplaintDetails(complaintId);
		
	}
	
	@RequestMapping(value = "/updateComplaintById" , method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer updateComplaintById(Integer complaintId,String status){
		
		return complaintsManagementService.updateComplaint(complaintId, status);
		
	}
}
