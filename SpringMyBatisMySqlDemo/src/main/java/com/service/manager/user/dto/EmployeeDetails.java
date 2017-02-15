package com.service.manager.user.dto;

public class EmployeeDetails {
	private Integer id;
	private String firstName;
	private String lasttName;
	private String address;
	
	
	
	public EmployeeDetails() {

	}
	public EmployeeDetails(Integer id, String firstName, String lasttName,
			String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLasttName() {
		return lasttName;
	}
	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
