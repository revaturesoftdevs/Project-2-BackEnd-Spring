package com.group5devs.project2.service;
import com.group5devs.project2.pojo.*;
import com.group5devs.project2.exceptions.*;

import java.util.List;




public interface ManagerService {
	
	ManagerPojo Login(ManagerPojo managerPojo) throws SystemException;
	
	List<ReimbursementPojo> viewAllPendingReimbursements(int mgrId) throws SystemException, NoPendingRequestException;
	
	List<ReimbursementPojo> viewAllResolvedReimbursements(int mgrId) throws SystemException, NoResolvedRequestException;
	
	List<ReimbursementPojo> viewIndividualReimbursement(int mgrId, int empId) throws SystemException;
	
	boolean approveReimbursement(int empId,int reimbursementId) throws SystemException;
	
	boolean denyReimbursement(int empId,int reimbursementId) throws SystemException;
	
	List<EmployeePojo> viewAllEmployees(int mgrId) throws SystemException;
	

	EmployeePojo registerAnEmployee(EmployeePojo employeePojo);

	public EmployeePojo individualEmployee(int mgrId, int empId) throws SystemException;
	
	public List<ReimbursementPojo> individualEmployeeReimbursement(int mgrId, int empId) throws SystemException, NoRequestException;

}