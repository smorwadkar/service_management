package com.service.manager.user.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.manager.user.dto.ComplaintsDetails;
import com.service.manager.user.dto.FullProductDetails;
import com.service.manager.user.dto.FullRequestDetails;
import com.service.manager.user.persistence.Complaints;
import com.service.manager.user.persistence.ComplaintsExample;
import com.service.manager.user.persistence.Product;
import com.service.manager.user.persistence.RequestDetails;
import com.service.manager.user.persistence.User;
import com.service.manager.user.persistence.UserExample;
import com.service.manager.user.persistence.mapper.ComplaintsMapper;
import com.service.manager.user.persistence.mapper.ProductCategoryMapper;
import com.service.manager.user.persistence.mapper.ProductMapper;
import com.service.manager.user.persistence.mapper.ProductSubCategoryMapper;
import com.service.manager.user.persistence.mapper.ProductTypeMapper;
import com.service.manager.user.persistence.mapper.RequestDetailsMapper;
import com.service.manager.user.persistence.mapper.UserMapper;
import com.service.manager.user.service.ProductManagementService;

@RestController
@RequestMapping("/api/request")
public class RequestManagementController {

	@Autowired
	ComplaintsMapper complaintsMapper;

	@Autowired
	ProductManagementService productManagementService;

	@Autowired
	RequestDetailsMapper requestDetailsMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ProductTypeMapper productTypeMapper;
	
	@Autowired
	ProductSubCategoryMapper productSubCategoryMapper;
	
	@Autowired
	ProductCategoryMapper productCategoryMapper;

	@RequestMapping(value = "/createRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public RequestDetails createRequest(@RequestParam("userId") Long userMobileNo,
			@RequestParam("comments") String comments,
			@RequestParam("complaintStatus") String complaintStatus,
			@RequestParam("categoryName") String categoryName,
			@RequestParam("subCategoryName") String subCategoryName,
			@RequestParam("typeName") String typeName) {
		// Add Conmplaint

		ComplaintsDetails complaintsDetails = new ComplaintsDetails();
		complaintsDetails.setComments(comments);
		complaintsDetails.setComplaintStatus(complaintStatus);
		complaintsDetails.setCreationTime(new Date());
		complaintsDetails.setLastUpdateTime(new Date());

		complaintsMapper.insert(populateComplaint(complaintsDetails));

		ComplaintsExample complaintsExample = new ComplaintsExample();
		complaintsExample
				.createCriteria()
				.andCommentsEqualTo(comments)
				.andComplaintStatusEqualTo(complaintStatus)
				.andCreateDateEqualTo(complaintsDetails.getCreationTime())
				.andLastUpdateDateEqualTo(complaintsDetails.getLastUpdateTime());

		List<Complaints> complants = complaintsMapper.selectByExample(complaintsExample);
		/*List<Complaints> complants = complaintsMapper
				.getComplaintByDetails(comments, complaintStatus, getDateForDB(complaintsDetails.getCreationTime()), getDateForDB(complaintsDetails.getLastUpdateTime()));*/
		
		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileNoEqualTo(userMobileNo);
		
		List<User> users = userMapper.selectByExample(userExample);
		
		// Add Request Details
		Product product = productManagementService.addProduct(categoryName,
				subCategoryName, typeName);

		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setProductId(product.getProductId());
		requestDetails.setUserId(users.get(0).getUserId());
		requestDetails.setRequestId(complants.get(0).getComplaintId());

		requestDetailsMapper.insert(requestDetails);

		/*
		 * RequestDetailsExample requestDetailsExample = new
		 * RequestDetailsExample();
		 * requestDetailsExample.createCriteria().andRequestIdEqualTo
		 * (requestDetails.getRequestId());
		 */

		// requestDetailsMapper.selectByPrimaryKey(requestDetails.getRequestId());
		

		return requestDetailsMapper.selectByPrimaryKey(requestDetails
				.getRequestId());
	}

	private Complaints populateComplaint(ComplaintsDetails complaintsDetails) {
		Complaints complaints = new Complaints();
		complaints.setComments(complaintsDetails.getComments());
		complaints.setComplaintStatus(complaintsDetails.getComplaintStatus());
		complaints.setCreateDate(complaintsDetails
				.getCreationTime());
		complaints.setLastUpdateDate(complaintsDetails
				.getLastUpdateTime());

		return complaints;
	}

	/*
	 * private RequestsDetails populateRequestsDetailsDTO(RequestDetails
	 * requestDetails){ RequestsDetails requests = new RequestsDetails();
	 * 
	 * requests.s
	 * 
	 * return complaints; }
	 */

	
	@RequestMapping(value = "/allRequests" , method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FullRequestDetails> getAllRequests(){
		
		List<RequestDetails> requestDetailsList = requestDetailsMapper.fetchAllRequestDetails();
		
		List<FullRequestDetails> fullRequestDetailsList = new ArrayList<FullRequestDetails>();
		
		for (RequestDetails requestDetails : requestDetailsList) {
			FullRequestDetails fullRequestDetails = new FullRequestDetails();
			
			Complaints complaints = complaintsMapper.selectByPrimaryKey(requestDetails.getRequestId());
			fullRequestDetails.setComplaint(complaints);
			
			Product product = productMapper.selectByPrimaryKey(requestDetails.getProductId());
			FullProductDetails fullProductDetails = new FullProductDetails();
			fullProductDetails.setProductCategory(productCategoryMapper.selectByPrimaryKey(product.getCategoryId()));
			fullProductDetails.setProductSubCategory(productSubCategoryMapper.selectByPrimaryKey(product.getSubCategoryId()));
			fullProductDetails.setProductType(productTypeMapper.selectByPrimaryKey(product.getTypeId()));
			
			fullRequestDetails.setFullProductDetails(fullProductDetails);
			
			User user = userMapper.selectByPrimaryKey(requestDetails.getUserId());
			fullRequestDetails.setUserId(user);
			
			fullRequestDetailsList.add(fullRequestDetails);
		}
		
		return fullRequestDetailsList;
	}
	
	
	@RequestMapping(value = "/allRequestsForUser" , method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FullRequestDetails> getAllRequestsForUser(@RequestParam("userMobileNo") Long userMobileNo){
		
		List<RequestDetails> requestDetailsList = requestDetailsMapper.fetchAllRequestDetailsForUser(userMobileNo);
		
		List<FullRequestDetails> fullRequestDetailsList = new ArrayList<FullRequestDetails>();
		
		for (RequestDetails requestDetails : requestDetailsList) {
			FullRequestDetails fullRequestDetails = new FullRequestDetails();
			
			Complaints complaints = complaintsMapper.selectByPrimaryKey(requestDetails.getRequestId());
			fullRequestDetails.setComplaint(complaints);
			
			Product product = productMapper.selectByPrimaryKey(requestDetails.getProductId());
			FullProductDetails fullProductDetails = new FullProductDetails();
			fullProductDetails.setProductCategory(productCategoryMapper.selectByPrimaryKey(product.getCategoryId()));
			fullProductDetails.setProductSubCategory(productSubCategoryMapper.selectByPrimaryKey(product.getSubCategoryId()));
			fullProductDetails.setProductType(productTypeMapper.selectByPrimaryKey(product.getTypeId()));
			
			fullRequestDetails.setFullProductDetails(fullProductDetails);
			
			User user = userMapper.selectByPrimaryKey(requestDetails.getUserId());
			fullRequestDetails.setUserId(user);
			
			fullRequestDetailsList.add(fullRequestDetails);
		}
		
		return fullRequestDetailsList;
	}
}
