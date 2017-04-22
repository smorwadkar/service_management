package com.sajag.manager.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ComplaintsDetails {
	private Integer complaintId;
	private String complaintStatus;
	private Date creationTime;
	private Date lastUpdateTime;
	private String comments;
	private List<MultipartFile> images;
	private Integer departmentId;
	
	
	/**
	 * @return the complaintId
	 */
	public Integer getComplaintId() {
		return complaintId;
	}
	/**
	 * @param complaintId the complaintId to set
	 */
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	/**
	 * @return the complaintStatus
	 */
	public String getComplaintStatus() {
		return complaintStatus;
	}
	/**
	 * @param complaintStatus the complaintStatus to set
	 */
	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	/**
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the images
	 */
	public List<MultipartFile> getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	/**
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
}
