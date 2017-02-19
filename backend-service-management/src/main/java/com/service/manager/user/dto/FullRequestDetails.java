package com.service.manager.user.dto;

import com.service.manager.user.persistence.Complaints;
import com.service.manager.user.persistence.User;

public class FullRequestDetails {
	private Integer requestId;
	private User userId;
	private Complaints complaint;
	private FullProductDetails fullProductDetails;
	
	
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Complaints getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaints complaint) {
		this.complaint = complaint;
	}
	public FullProductDetails getFullProductDetails() {
		return fullProductDetails;
	}
	public void setFullProductDetails(FullProductDetails fullProductDetails) {
		this.fullProductDetails = fullProductDetails;
	}
}
