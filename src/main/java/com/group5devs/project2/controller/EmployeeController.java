package com.group5devs.project2.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.group5devs.project2.exceptions.NoPendingRequestException;
import com.group5devs.project2.exceptions.NoResolvedRequestException;
import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.pojo.ReimbursementPojo;
import com.group5devs.project2.service.EmployeeService;
import com.group5devs.project2.service.ReimbursementService;

@CrossOrigin
@RestController
@RequestMapping("api")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
//	tested
	@GetMapping("emp-pending-reimbursements/{eid}")
	public List<ReimbursementPojo> getAllPendingReimbursements(@PathVariable("eid") int empId)throws NoPendingRequestException{
		return employeeService.viewPendingReimbursements(empId);
	}
	
//	tested
	@GetMapping("emp-resolved-reimbursements/{eid}")
	public List<ReimbursementPojo> getAllReslovedReimbursements(@PathVariable("eid") short empId)throws NoResolvedRequestException{
		return employeeService.viewResolvedReimbursements(empId);
	}
	
//	tested
	@GetMapping("emp-profile/{eid}")
	public EmployeePojo getEmployeeProfile(@PathVariable("eid") int empId) {
		return employeeService.viewEmployeeProfile(empId);
	}
	
//	tested
	@PutMapping("update-emp")
	public EmployeePojo updateEmployeeProfile(@RequestBody EmployeePojo employeePojo) {
		return employeeService.modifyEmployee(employeePojo);
	}
	
//	tested
	@PostMapping("add-reimbursement")
	public ReimbursementPojo addReimbursement(@RequestBody ReimbursementPojo reimbursementPojo) {
		return employeeService.addReimbursment(reimbursementPojo);
	}
	
//	tested
	@PostMapping("login-employee")
	public EmployeePojo loginEmployee(@RequestBody EmployeePojo employeepojo) {
		return employeeService.login(employeepojo);
	}
	

	
}
