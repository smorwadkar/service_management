package com.service.manager.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.repository.EmployeeRepository;
import com.shiv.mybatis.demo.component.persistence.Employee;
import com.shiv.mybatis.demo.component.persistence.mapper.EmployeeMapper;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

	@Autowired
	EmployeeMapper employeeMapper = null;
	
	@Override
	public int insertEmployee(Employee employee) {
		return employeeMapper.insertSelective(employee);
	}

}
