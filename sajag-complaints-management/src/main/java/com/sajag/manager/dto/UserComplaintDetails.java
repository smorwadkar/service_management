package com.sajag.manager.dto;

import java.util.List;

public class UserComplaintDetails {
	private String mobileNo;
	private List<ComplaintsDetails> userComplaints;
	
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the userComplaints
	 */
	public List<ComplaintsDetails> getUserComplaints() {
		return userComplaints;
	}
	/**
	 * @param userComplaints the userComplaints to set
	 */
	public void setUserComplaints(List<ComplaintsDetails> userComplaints) {
		this.userComplaints = userComplaints;
	}
}
