package com.service.manager.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.manager.user.dto.EmployeeDetails;
import com.service.manager.user.repository.EmployeeRepository;
import com.service.manager.user.service.EmployeeManagementService;
import com.shiv.mybatis.demo.component.persistence.Employee;

@Service
@Transactional("mysqlTransactionManager")
public class EmployeeManagementServiceImpl implements EmployeeManagementService{

	@Autowired
	EmployeeRepository employeeRepository;
	

	@Override
	public int createEmployee(EmployeeDetails employeeDetails) {
		Employee employee = populateEmployee(employeeDetails);
		return employeeRepository.insertEmployee(employee);
	}
	
	private Employee populateEmployee(EmployeeDetails employeeDetails){
		Employee employee = new Employee();
		employee.setId(employeeDetails.getId().longValue());
		employee.setFname(employeeDetails.getFirstName());
		employee.setLname(employeeDetails.getLasttName());
		employee.setAddress(employeeDetails.getAddress());
		return employee;
	}
}
