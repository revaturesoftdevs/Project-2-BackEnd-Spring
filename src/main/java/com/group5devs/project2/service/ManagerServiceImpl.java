package com.group5devs.project2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.group5devs.project2.dao.*;
import com.group5devs.project2.entity.EmployeeEntity;
import com.group5devs.project2.entity.ManagerEntity;
import com.group5devs.project2.entity.ReimbursementEntity;
import com.group5devs.project2.pojo.*;
import com.group5devs.project2.service.*;

import com.group5devs.project2.exceptions.*;

@Service
public class ManagerServiceImpl implements ManagerService {
	// since we are working with spring boot, there is no need to configure the
	// login framework
	// creating the logger variable to log information
	// choose slf4j logger to import and this in turn uses LogBack framework for
	// logging
	final static Logger LOG = LoggerFactory.getLogger(ManagerServiceImpl.class);
	@Autowired
	ManagerDao managerDao;

	@Autowired
	ReimbursementDao reimbursementDao;

	@Override
	public ManagerPojo Login(ManagerPojo managerPojo) throws SystemException {
		String incomingUsername = managerPojo.getMgrUserName();
		String incomingPassword = managerPojo.getMgrPassword();
		ManagerEntity managerEntity = managerDao.findByMgrUserNameAndMgrPassword(incomingUsername, incomingPassword);
		ManagerPojo returnedManagerPojo = new ManagerPojo();
		BeanUtils.copyProperties(managerEntity, returnedManagerPojo);
		return returnedManagerPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllPendingReimbursements(int mgrId) throws SystemException {
		LOG.info("Entered View all pending reimbursements method in the service");
		System.out.println("mgrid:" + mgrId);

		List<ReimbursementEntity> allPendingEntity = managerDao.findAllPendingReimbursementStatusAndMgrId(mgrId);
		System.out.println("completed");
		List<ReimbursementPojo> allPendingPojo = new ArrayList<>();
		for (ReimbursementEntity fetchedPendingEntity : allPendingEntity) {
			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(fetchedPendingEntity.getReimbursementId(),
					fetchedPendingEntity.getEmpId(), fetchedPendingEntity.getMgrId(),
					fetchedPendingEntity.getReimbursementDesc(), fetchedPendingEntity.getReimbursementAmt(),
					fetchedPendingEntity.getReimbursementStatus());
			allPendingPojo.add(returnReimbursementPojo);
		}
		LOG.info("Exited the view pending reimbursment in the service layer");
		return allPendingPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedReimbursements(int mgrId) throws SystemException {
		List<ReimbursementPojo> allResolvedPojo = new ArrayList<>();
		List<ReimbursementEntity> allResolvedEntity = managerDao.findAllResolvedReimbursementStatusAndMgrId(mgrId);
		System.out.println(mgrId);
		for (ReimbursementEntity fetchedResolvedEntity : allResolvedEntity) {
			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedResolvedEntity.getReimbursementId(), fetchedResolvedEntity.getEmpId(),
					fetchedResolvedEntity.getMgrId(), fetchedResolvedEntity.getReimbursementDesc(),
					fetchedResolvedEntity.getReimbursementAmt(), fetchedResolvedEntity.getReimbursementStatus());
			allResolvedPojo.add(returnReimbursementPojo);
		}

		System.out.println(mgrId);
		return allResolvedPojo;
	}

	@Override
	public List<ReimbursementPojo> viewIndividualReimbursement(int mgrId, int empId) throws SystemException {
		List<ReimbursementEntity> allIndividualEntity = managerDao.findAllByMgrIdAndEmpId(mgrId, empId);
		List<ReimbursementPojo> allResolvedPojo = new ArrayList<>();
		for (ReimbursementEntity fetchedResolvedEntity : allIndividualEntity) {
			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedResolvedEntity.getReimbursementId(), fetchedResolvedEntity.getEmpId(),
					fetchedResolvedEntity.getMgrId(), fetchedResolvedEntity.getReimbursementDesc(),
					fetchedResolvedEntity.getReimbursementAmt(), fetchedResolvedEntity.getReimbursementStatus());
			allResolvedPojo.add(returnReimbursementPojo);
		}
		return allResolvedPojo;
	}

	@Override
	public boolean approveReimbursement(int empId, int reimbursementId) throws SystemException {
		// 2 step process

		// 1st - fetch the reimbursentment entoty by specfying the reimburse id
		Optional<ReimbursementEntity> fetchedReimburmentEntity = reimbursementDao.findById(reimbursementId);
		ReimbursementEntity reimEntity = fetchedReimburmentEntity.get();

		// 2nd -change the status of reimbursement to approved and save the
		// reimbursement entity
		reimEntity.setReimbursementStatus("approved");
		reimbursementDao.save(reimEntity);

		return true;
	}

	@Override
	public boolean denyReimbursement(int empId, int reimbursementId) throws SystemException {
		// 2 step process

		// 1st - fetch the reimbursentment entoty by specfying the reimburse id
		Optional<ReimbursementEntity> fetchedReimburmentEntity = reimbursementDao.findById(reimbursementId);
		ReimbursementEntity reimEntity = fetchedReimburmentEntity.get();

		// 2nd -change the status of reimbursement to approved and save the
		// reimbursement entity
		reimEntity.setReimbursementStatus("denied");
		reimbursementDao.save(reimEntity);
		return true;
	}

	@Override
	public List<EmployeePojo> viewAllEmployees(int mgrId) throws SystemException {

		List<EmployeeEntity> allEmployeeEntity = managerDao.findAllByMgrId(mgrId);
		List<EmployeePojo> allEmployeePojo = new ArrayList<>();
		for (EmployeeEntity fetchedEntity : allEmployeeEntity) {
			EmployeePojo returnEmployeePojo = new EmployeePojo(fetchedEntity.getEmpId(), fetchedEntity.getMgrId(),
					fetchedEntity.getEmpFirstName(), fetchedEntity.getEmpLastName(), fetchedEntity.getEmpUserName(),
					fetchedEntity.getEmpPassword());
			allEmployeePojo.add(returnEmployeePojo);
		}

		return allEmployeePojo;
	}

	public EmployeePojo individualEmployee(int mgrId, int empId) throws SystemException {

		EmployeeEntity employeeEntity = managerDao.findEmployee(mgrId, empId);
		EmployeePojo employeePojo = new EmployeePojo();

		BeanUtils.copyProperties(employeeEntity, employeePojo);
		return employeePojo;

	}

		public List <ReimbursementPojo> individualEmployeeReimbursement(int mgrId, int empId) throws SystemException {

		List <ReimbursementEntity> retrievedReimbursement = managerDao.findEmployeeReimb(mgrId, empId);
		List<ReimbursementPojo> returnedEmployeeReimbursements = new ArrayList<>();
		
		for(ReimbursementEntity eachEntity : retrievedReimbursement) {
			
			ReimbursementPojo returnPojo = new ReimbursementPojo(eachEntity.getReimbursementId(),eachEntity.getEmpId(),eachEntity.getMgrId(),eachEntity.getReimbursementDesc(),eachEntity.getReimbursementAmt(),eachEntity.getReimbursementStatus());
			returnedEmployeeReimbursements.add(returnPojo);
		}
	
		return returnedEmployeeReimbursements;

	}

}