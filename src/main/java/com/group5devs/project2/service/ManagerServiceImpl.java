package service;

import java.util.List;

import dao.ManagerDao;
import dao.ManagerDaoImpl;
import exception.EmptyStoreException;
import exception.SystemException;
import model.EmployeePojo;
import model.ManagerPojo;
import model.ReimbursementPojo;

public class ManagerServiceImpl implements ManagerService {

	ManagerDao managerDao;

	public ManagerServiceImpl() {
		managerDao = new ManagerDaoImpl();
	}

	public ManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	public ManagerPojo Login(ManagerPojo managerPojo) throws SystemException {
		return managerDao.Login(managerPojo);
	}

	@Override
	public List<ReimbursementPojo> viewAllPendingReimbursements(int mgrId)
			throws SystemException {
		return managerDao.viewAllPendingReimbursements(mgrId);
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedReimbursements(int mgrId)
			throws SystemException, EmptyStoreException {
		return managerDao.viewAllResolvedReimbursements(mgrId);
	}

	@Override
	public List<ReimbursementPojo> viewIndividualReimbursement(int mgrId, int empId)
			throws SystemException, EmptyStoreException {
		return managerDao.viewIndividualReimbursement(mgrId, empId);
	}


	@Override
	public boolean approveReimbursement(int empId,int reimbursementId) throws SystemException {
		return managerDao.approveReimbursement(empId, reimbursementId);
	}

	@Override
	public boolean denyReimbursement(int empId,int reimbursementId) throws SystemException {
		return managerDao.denyReimbursement(empId,reimbursementId);
	}

	@Override
	public List<EmployeePojo> viewAllEmployees(int mgrId) throws SystemException, EmptyStoreException {
		return managerDao.viewAllEmployees(mgrId);
	}
}