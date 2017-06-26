package com.service.manager.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.manager.user.dto.ComplaintsDetails;
import com.service.manager.user.dto.FullProductDetails;
import com.service.manager.user.dto.FullRequestDetails;
import com.service.manager.user.persistence.Complaints;
import com.service.manager.user.persistence.Product;
import com.service.manager.user.persistence.RequestDetails;
import com.service.manager.user.persistence.User;
import com.service.manager.user.persistence.mapper.ComplaintsMapper;
import com.service.manager.user.persistence.mapper.ProductCategoryMapper;
import com.service.manager.user.persistence.mapper.ProductMapper;
import com.service.manager.user.persistence.mapper.ProductSubCategoryMapper;
import com.service.manager.user.persistence.mapper.ProductTypeMapper;
import com.service.manager.user.persistence.mapper.RequestDetailsMapper;
import com.service.manager.user.persistence.mapper.UserMapper;
import com.service.manager.user.repository.RequestManagementRepository;
import com.service.manager.user.service.ComplaintsManagementService;
import com.service.manager.user.service.ProductManagementService;
import com.service.manager.user.service.RequestManagementService;
import com.service.manager.user.service.UserManagementService;

@Service
public class RequestManagementServiceImpl implements RequestManagementService {

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
	
	@Autowired
	RequestManagementRepository requestManagementRepository;

	@Autowired
	UserManagementService userManagementService;
	
	@Autowired
	ComplaintsManagementService complaintsManagementService;
	
	@Override
	public RequestDetails createRequest(Long userMobileNo, String comments,
			String complaintStatus, String categoryName,
			String subCategoryName, String typeName) {
		// Add Conmplaint

		ComplaintsDetails complaintsDetails = new ComplaintsDetails();
		complaintsDetails.setComments(comments);
		complaintsDetails.setComplaintStatus(complaintStatus);
		complaintsDetails.setCreationTime(new Date());
		complaintsDetails.setLastUpdateTime(new Date());

		/*complaintsMapper.insert(populateComplaint(complaintsDetails));*/
		
		int createdComplaintId = complaintsManagementService.insertComplaint(complaintsDetails);

		/*ComplaintsExample complaintsExample = new ComplaintsExample();
		complaintsExample
				.createCriteria()
				.andCommentsEqualTo(comments)
				.andComplaintStatusEqualTo(complaintStatus)
				.andCreateDateEqualTo(complaintsDetails.getCreationTime())
				.andLastUpdateDateEqualTo(complaintsDetails.getLastUpdateTime());

		List<Complaints> complants = complaintsMapper
				.selectByExample(complaintsExample);*/
		
		List<Complaints> complants = complaintsManagementService.selectComplaintByCriteria(complaintsDetails);
		
		/*UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileNoEqualTo(userMobileNo);

		List<User> users = userMapper.selectByExample(userExample);
		*/
		
		List<User> users = userManagementService.selectUserByMobileNo(userMobileNo);

		// Add Request Details
		Product product = productManagementService.addProduct(categoryName,
				subCategoryName, typeName);

		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setProductId(product.getProductId());
		requestDetails.setUserId(users.get(0).getUserId());
//		requestDetails.setRequestId(complants.get(0).getComplaintId());
		requestDetails.setRequestId(createdComplaintId);

		/*requestDetailsMapper.insert(requestDetails);*/

		requestManagementRepository.insertRequest(requestDetails);

/*		return requestDetailsMapper.selectByPrimaryKey(requestDetails
				.getRequestId());*/
		
		//As complaint Id will same as request Id
		return requestManagementRepository.getRequestDetailsByCriteria(requestDetails.getRequestId());
	}

	@Override
	public List<FullRequestDetails> getAllRequests() {
		List<RequestDetails> requestDetailsList = requestDetailsMapper
				.fetchAllRequestDetails();

		List<FullRequestDetails> fullRequestDetailsList = new ArrayList<FullRequestDetails>();

		for (RequestDetails requestDetails : requestDetailsList) {
			FullRequestDetails fullRequestDetails = new FullRequestDetails();

			fullRequestDetails.setRequestId(requestDetails.getRequestId());
			Complaints complaints = complaintsMapper
					.selectByPrimaryKey(requestDetails.getRequestId());
			fullRequestDetails.setComplaint(complaints);

			Product product = productMapper.selectByPrimaryKey(requestDetails
					.getProductId());
			FullProductDetails fullProductDetails = new FullProductDetails();
			fullProductDetails.setProductCategory(productCategoryMapper
					.selectByPrimaryKey(product.getCategoryId()));
			fullProductDetails.setProductSubCategory(productSubCategoryMapper
					.selectByPrimaryKey(product.getSubCategoryId()));
			fullProductDetails.setProductType(productTypeMapper
					.selectByPrimaryKey(product.getTypeId()));

			fullRequestDetails.setFullProductDetails(fullProductDetails);

			User user = userMapper.selectByPrimaryKey(requestDetails
					.getUserId());
			fullRequestDetails.setUserId(user);

			fullRequestDetailsList.add(fullRequestDetails);
		}

		return fullRequestDetailsList;
	}

	@Override
	public List<FullRequestDetails> getAllRequestsForUser(Long userMobileNo) {
		List<RequestDetails> requestDetailsList = requestDetailsMapper
				.fetchAllRequestDetailsForUser(userMobileNo);

		List<FullRequestDetails> fullRequestDetailsList = new ArrayList<FullRequestDetails>();

		for (RequestDetails requestDetails : requestDetailsList) {
			FullRequestDetails fullRequestDetails = new FullRequestDetails();

			fullRequestDetails.setRequestId(requestDetails.getRequestId());
			Complaints complaints = complaintsMapper
					.selectByPrimaryKey(requestDetails.getRequestId());
			fullRequestDetails.setComplaint(complaints);

			Product product = productMapper.selectByPrimaryKey(requestDetails
					.getProductId());
			FullProductDetails fullProductDetails = new FullProductDetails();
			fullProductDetails.setProductCategory(productCategoryMapper
					.selectByPrimaryKey(product.getCategoryId()));
			fullProductDetails.setProductSubCategory(productSubCategoryMapper
					.selectByPrimaryKey(product.getSubCategoryId()));
			fullProductDetails.setProductType(productTypeMapper
					.selectByPrimaryKey(product.getTypeId()));

			fullRequestDetails.setFullProductDetails(fullProductDetails);

			User user = userMapper.selectByPrimaryKey(requestDetails
					.getUserId());
			fullRequestDetails.setUserId(user);

			fullRequestDetailsList.add(fullRequestDetails);
		}

		return fullRequestDetailsList;
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
	public FullRequestDetails getRequestDetailsById(Integer requestId) {
		
		RequestDetails requestDetails = requestDetailsMapper.selectByPrimaryKey(requestId);
		
		
		FullRequestDetails fullRequestDetails = new FullRequestDetails();
		fullRequestDetails.setRequestId(requestDetails.getRequestId());
		
		Complaints complaints = complaintsMapper
				.selectByPrimaryKey(requestDetails.getRequestId());
		fullRequestDetails.setComplaint(complaints);

		Product product = productMapper.selectByPrimaryKey(requestDetails
				.getProductId());
		FullProductDetails fullProductDetails = new FullProductDetails();
		fullProductDetails.setProductCategory(productCategoryMapper
				.selectByPrimaryKey(product.getCategoryId()));
		fullProductDetails.setProductSubCategory(productSubCategoryMapper
				.selectByPrimaryKey(product.getSubCategoryId()));
		fullProductDetails.setProductType(productTypeMapper
				.selectByPrimaryKey(product.getTypeId()));

		fullRequestDetails.setFullProductDetails(fullProductDetails);

		User user = userMapper.selectByPrimaryKey(requestDetails
				.getUserId());
		fullRequestDetails.setUserId(user);
		
		return fullRequestDetails;
	}
}
