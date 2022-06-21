package com.group5devs.project2.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5devs.project2.dao.EmployeeDao;
import com.group5devs.project2.dao.ReimbursementDao;
import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.pojo.ReimbursementPojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.group5devs.project2.entity.*;
import com.group5devs.project2.exceptions.NoPendingRequestException;
import com.group5devs.project2.exceptions.NoResolvedRequestException;


@Service
public class EmployeeServiceImpl implements EmployeeService{
//	final static Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	ReimbursementDao reimbursementDao;
	
	public EmployeeServiceImpl() {
		
	}

	@Override
	public EmployeePojo login(EmployeePojo employeePojo) {
		EmployeePojo returnEmployeePojo;
		String employeeUserName = employeePojo.getEmpUserName();
		String employeePassword = employeePojo.getEmpPassword();
		EmployeeEntity employeeEntity = employeeDao.findByEmpUserNameAndEmpPassword(employeeUserName, employeePassword);
		if(employeeEntity.getEmpId() != 0) {
			returnEmployeePojo = new EmployeePojo();
			BeanUtils.copyProperties(employeeEntity, returnEmployeePojo);
		}else {
			return employeePojo;
		}
		return returnEmployeePojo;
		
	}

	@Override
	public List<ReimbursementPojo> viewPendingReimbursements(int empId) throws NoPendingRequestException {
//		LOG.info("Entered EmployeeserviceImpl in employeeservice");
		Iterable<Integer> emp_id;
		List<ReimbursementEntity> allPendingReimbursementEntity = reimbursementDao.findByEmpId(empId);
		List<ReimbursementPojo> allPendingReimbursementPojo = new ArrayList<ReimbursementPojo>();
		
		if(allPendingReimbursementEntity.isEmpty()) {
			throw new NoPendingRequestException();
		}
		for(ReimbursementEntity eachReimbursement : allPendingReimbursementEntity) {
			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(eachReimbursement.getReimbursementId(),eachReimbursement.getEmpId(),eachReimbursement.getMgrId(),eachReimbursement.getReimbursementDesc(),eachReimbursement.getReimbursementAmt(),eachReimbursement.getReimbursementStatus());
			allPendingReimbursementPojo.add(returnReimbursementPojo);
		}
		return allPendingReimbursementPojo;
	}

	@Override
	public List<ReimbursementPojo> viewResolvedReimbursements(short empId) throws NoResolvedRequestException {
//		LOG.info("entered viewResolvedReimbursements in service");
//		Iterable<Integer> emp_id;
		List<ReimbursementEntity> allResolvedReimbursementEntity = reimbursementDao.findByEmpId(empId);
		List<ReimbursementPojo> allResolvedReimbursementPojo = new ArrayList<ReimbursementPojo>();
		if(allResolvedReimbursementEntity.isEmpty()) {
			throw new NoResolvedRequestException();
		}
		for(ReimbursementEntity eachReimbursement : allResolvedReimbursementEntity) {
			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(eachReimbursement.getReimbursementId(),eachReimbursement.getEmpId(),eachReimbursement.getMgrId(),eachReimbursement.getReimbursementDesc(),eachReimbursement.getReimbursementAmt(),eachReimbursement.getReimbursementStatus());
			allResolvedReimbursementPojo.add(returnReimbursementPojo);
		}
//		LOG.info("Exited viewResolvedReimbursements in service");
		return allResolvedReimbursementPojo;
		
	}

	@Override
	public EmployeePojo viewEmployeeProfile(int empId) {
		Optional<EmployeeEntity> employeeEntity = employeeDao.findById(empId);
		EmployeePojo employeePojo = null;
		if(employeeEntity.isPresent()) {
			
			EmployeeEntity fetchedEmployeeEntity = employeeEntity.get();
			employeePojo = new EmployeePojo();
			BeanUtils.copyProperties(fetchedEmployeeEntity, employeePojo);
		}
		return employeePojo;
		
	}

	@Override
	public EmployeePojo modifyEmployee(EmployeePojo employeePojo) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeePojo, employeeEntity);
		
		//  now pass the bookEntity object to spring data jpa to be updated into the table
		EmployeeEntity returnedEmployeeEntity = employeeDao.save(employeeEntity);
		BeanUtils.copyProperties(returnedEmployeeEntity, employeePojo);
		
		return employeePojo;
	}

	@Override
	public ReimbursementPojo addReimbursment(ReimbursementPojo reimbursementPojo) {
		ReimbursementEntity reimbursementEntity = new ReimbursementEntity();
		BeanUtils.copyProperties(reimbursementPojo, reimbursementEntity);
		
		//  now pass the bookEntity object to spring data jpa to be updated into the table
		ReimbursementEntity returnedReimbursementEntity = reimbursementDao.saveAndFlush(reimbursementEntity);
		reimbursementPojo.setReimbursementId(returnedReimbursementEntity.getReimbursementId());
		
		return reimbursementPojo;
	}

	
	
}
