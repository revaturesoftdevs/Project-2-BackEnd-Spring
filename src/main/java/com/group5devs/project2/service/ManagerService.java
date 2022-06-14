package service;

import java.util.List;

import exception.EmptyStoreException;
import exception.SystemException;
import model.EmployeePojo;
import model.ManagerPojo;
import model.ReimbursementPojo;

public interface ManagerService {
	
	ManagerPojo Login(ManagerPojo managerPojo) throws SystemException;
	List<ReimbursementPojo> viewAllPendingReimbursements(int mgrId) throws SystemException;
	List<ReimbursementPojo> viewAllResolvedReimbursements(int mgrId) throws SystemException, EmptyStoreException;
	List<ReimbursementPojo> viewIndividualReimbursement(int mgrId, int empId) throws SystemException, EmptyStoreException;
	boolean approveReimbursement(int empId,int reimbursementId) throws SystemException;
	boolean denyReimbursement(int empId,int reimbursementId) throws SystemException;
	List<EmployeePojo> viewAllEmployees(int mgrId) throws SystemException, EmptyStoreException;
}