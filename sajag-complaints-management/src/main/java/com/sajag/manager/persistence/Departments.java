package com.sajag.manager.persistence;

public class Departments {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column departments.department_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    private Integer departmentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column departments.department_name
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    private String departmentName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column departments.department_id
     *
     * @return the value of departments.department_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column departments.department_id
     *
     * @param departmentId the value for departments.department_id
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column departments.department_name
     *
     * @return the value of departments.department_name
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column departments.department_name
     *
     * @param departmentName the value for departments.department_name
     *
     * @mbggenerated Fri Apr 21 12:47:03 IST 2017
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}