package com.sajag.manager.persistence;

import java.util.Date;

public class Complaints {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column complaints.complaint_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    private Integer complaintId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column complaints.complaint_status
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    private String complaintStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column complaints.create_date
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column complaints.last_update_date
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    private Date lastUpdateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column complaints.comments
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    private String comments;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column complaints.department_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    private Integer departmentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column complaints.complaint_id
     *
     * @return the value of complaints.complaint_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public Integer getComplaintId() {
        return complaintId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column complaints.complaint_id
     *
     * @param complaintId the value for complaints.complaint_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column complaints.complaint_status
     *
     * @return the value of complaints.complaint_status
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public String getComplaintStatus() {
        return complaintStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column complaints.complaint_status
     *
     * @param complaintStatus the value for complaints.complaint_status
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column complaints.create_date
     *
     * @return the value of complaints.create_date
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column complaints.create_date
     *
     * @param createDate the value for complaints.create_date
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column complaints.last_update_date
     *
     * @return the value of complaints.last_update_date
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column complaints.last_update_date
     *
     * @param lastUpdateDate the value for complaints.last_update_date
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column complaints.comments
     *
     * @return the value of complaints.comments
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public String getComments() {
        return comments;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column complaints.comments
     *
     * @param comments the value for complaints.comments
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column complaints.department_id
     *
     * @return the value of complaints.department_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column complaints.department_id
     *
     * @param departmentId the value for complaints.department_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}