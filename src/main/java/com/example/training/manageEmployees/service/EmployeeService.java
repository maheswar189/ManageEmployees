package com.example.training.manageEmployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.manageEmployees.bean.Employee;
import com.example.training.manageEmployees.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired //Requesting Spring Core for the object of employee repository.
	EmployeeRepository repository;
	
	public boolean createEmployee(Employee newEmp) {
		return repository.createEmployee(newEmp);
	}
	
	public Employee findEmployeeById(Integer empId) {
		return repository.findEmployeeById(empId);
	}

}
