package com.group5devs.project2.service;

import java.sql.SQLException;
import java.util.List;

import com.group5devs.project2.exceptions.NoPendingRequestException;
import com.group5devs.project2.exceptions.NoResolvedRequestException;
import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.pojo.ReimbursementPojo;



public interface EmployeeService {
	
	EmployeePojo login (EmployeePojo employeePojo);
	
	List<ReimbursementPojo> viewPendingReimbursements (int empId) throws NoPendingRequestException;
	
	List<ReimbursementPojo> viewResolvedReimbursements (short empId)throws NoResolvedRequestException;
	
	EmployeePojo viewEmployeeProfile (int empId);
	
	EmployeePojo modifyEmployee(EmployeePojo employeePojo);

	ReimbursementPojo addReimbursment(ReimbursementPojo reimburse);


}
