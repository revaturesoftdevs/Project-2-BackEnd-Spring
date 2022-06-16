package com.group5devs.project2.dao;

import java.util.List;

import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.pojo.ManagerPojo;
import com.group5devs.project2.pojo.ReimbursementPojo;



public interface ManagerDao {
	
	ManagerPojo Login(ManagerPojo managerPojo); 
	List<ReimbursementPojo> viewAllPendingReimbursements(int mgrId); 
	List<ReimbursementPojo> viewAllResolvedReimbursements(int mgrId); 
	List<ReimbursementPojo> viewIndividualReimbursement(int mgrId, int empId); 
	List<EmployeePojo> viewAllEmployees(int mgrId);
	boolean approveReimbursement(int empId,int reimbursementId);
	boolean denyReimbursement(int empId,int reimbursementId);

}