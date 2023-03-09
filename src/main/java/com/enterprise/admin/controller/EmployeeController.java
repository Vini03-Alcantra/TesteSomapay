package com.enterprise.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.admin.dto.EmployeeDTO;
import com.enterprise.admin.dto.EmployeeResponseDTO;
import com.enterprise.admin.entities.Employee;
import com.enterprise.admin.entities.Enterprise;
import com.enterprise.admin.service.EmployeeService;
import com.enterprise.admin.service.EnterpriseService;

@RestController
@RequestMapping(value = "/funcionario", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EnterpriseService enterpriseService;
	
	@GetMapping(value = "/{id}")
	public Employee getEmployee(@PathVariable(name = "id") long id){
		Employee employee = this.employeeService.getEmployeeByID(id);
		return employee;
	}
	
}
