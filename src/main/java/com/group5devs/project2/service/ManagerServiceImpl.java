package com.group5devs.project2.service;

import java.util.List;

import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.pojo.ManagerPojo;
import com.group5devs.project2.pojo.ReimbursementPojo;



public class ManagerServiceImpl implements ManagerService {

	@Override
	public ManagerPojo Login(ManagerPojo managerPojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementPojo> viewAllPendingReimbursements(int mgrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedReimbursements(int mgrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementPojo> viewIndividualReimbursement(int mgrId, int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveReimbursement(int empId, int reimbursementId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean denyReimbursement(int empId, int reimbursementId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EmployeePojo> viewAllEmployees(int mgrId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}