package com.service.manager.user.repository;

import com.service.manager.user.persistence.RequestDetails;

public interface RequestManagementRepository {
	public Integer insertRequest(RequestDetails requestDetails);
	
	public RequestDetails getRequestDetailsByCriteria(Integer requestId);
}
