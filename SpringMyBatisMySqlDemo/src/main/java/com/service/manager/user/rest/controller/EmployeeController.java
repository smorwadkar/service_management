package com.service.manager.user.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.manager.user.dto.EmployeeDetails;
import com.service.manager.user.service.EmployeeManagementService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeManagementService employeeManagementService;
	
	@RequestMapping(value = "/employee" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public int getEmployee(){
		
		EmployeeDetails employee = new EmployeeDetails(1, "Shiv", "M", "Pune");
		
		return employeeManagementService.createEmployee(employee);
	}
}
