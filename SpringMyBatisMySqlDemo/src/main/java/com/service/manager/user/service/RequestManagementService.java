package com.service.manager.user.service;

import java.util.List;

import com.service.manager.user.dto.FullRequestDetails;
import com.service.manager.user.persistence.RequestDetails;

public interface RequestManagementService {
	
	public RequestDetails createRequest(Long userMobileNo,String comments,String complaintStatus,String categoryName,String subCategoryName,String typeName);
	
	public List<FullRequestDetails> getAllRequests();
	
	public List<FullRequestDetails> getAllRequestsForUser(Long userMobileNo);
	
	public FullRequestDetails getRequestDetailsById(Integer requestId);
}
