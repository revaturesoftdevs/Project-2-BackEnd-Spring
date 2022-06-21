package com.group5devs.project2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.group5devs.project2.dao.ManagerDao;
import com.group5devs.project2.dao.ReimbursementDao;
import com.group5devs.project2.entity.EmployeeEntity;
import com.group5devs.project2.entity.ManagerEntity;
import com.group5devs.project2.entity.ReimbursementEntity;
import com.group5devs.project2.exceptions.NoPendingRequestException;
import com.group5devs.project2.exceptions.NoRequestException;
import com.group5devs.project2.exceptions.NoResolvedRequestException;
import com.group5devs.project2.exceptions.SystemException;
import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.pojo.ManagerPojo;
import com.group5devs.project2.pojo.ReimbursementPojo;

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
	private JavaMailSender javaMailSender;

	@Autowired
	ReimbursementDao reimbursementDao;
	
//	public void sendEmail(String toEmail, String subject, String body) {
//		
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("devarianil415@gmaul.com");
//		message.setTo(toEmail);
//		message.setText(body);
//		message.setSubject(subject);
//		
//		mailSender.send(message);
//		
//		System.out.println("mail sent successfully..");
//		
//	}
	
	

	@Override
	public ManagerPojo Login(ManagerPojo managerPojo) throws SystemException {
		LOG.info("Entered Login() in ManagerServiceImpl...");
		String incomingUsername = managerPojo.getMgrUserName();
		String incomingPassword = managerPojo.getMgrPassword();
		ManagerEntity managerEntity = managerDao.findByMgrUserNameAndMgrPassword(incomingUsername, incomingPassword);
		ManagerPojo returnedManagerPojo = new ManagerPojo();
		BeanUtils.copyProperties(managerEntity, returnedManagerPojo);
		LOG.info("Exited Login() in ManagerServiceImpl...");
		return returnedManagerPojo;
	}

	@Override

	public List<ReimbursementPojo> viewAllPendingReimbursements(int mgrId) throws SystemException, NoPendingRequestException {
		LOG.info("Entered View all pending reimbursements method in the service");

	
		System.out.println("mgrid:" + mgrId);

		List<ReimbursementEntity> allPendingEntity = managerDao.findAllPendingReimbursementStatusAndMgrId(mgrId);
		System.out.println("completed");
		List<ReimbursementPojo> allPendingPojo = new ArrayList<>();
		
		if(allPendingEntity.isEmpty()) {
			throw new NoPendingRequestException();
		}
		for (ReimbursementEntity fetchedPendingEntity : allPendingEntity) {
			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(fetchedPendingEntity.getReimbursementId(),
					fetchedPendingEntity.getEmpId(), fetchedPendingEntity.getMgrId(),
					fetchedPendingEntity.getReimbursementDesc(), fetchedPendingEntity.getReimbursementAmt(),
					fetchedPendingEntity.getReimbursementStatus());
			allPendingPojo.add(returnReimbursementPojo);
		}
		LOG.info("Exited viewAllPendingReimbursements() in ManagerServiceImpl...");
		return allPendingPojo;
	}

	@Override

	public List<ReimbursementPojo> viewAllResolvedReimbursements(int mgrId) throws SystemException, NoResolvedRequestException{

		List<ReimbursementPojo> allResolvedPojo = new ArrayList<>();
		List<ReimbursementEntity> allResolvedEntity = managerDao.findAllResolvedReimbursementStatusAndMgrId(mgrId);
		System.out.println(mgrId);
		if(allResolvedEntity.isEmpty()) {
			throw new NoResolvedRequestException();
		}
		for (ReimbursementEntity fetchedResolvedEntity : allResolvedEntity) {
			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedResolvedEntity.getReimbursementId(), fetchedResolvedEntity.getEmpId(),
					fetchedResolvedEntity.getMgrId(), fetchedResolvedEntity.getReimbursementDesc(),
					fetchedResolvedEntity.getReimbursementAmt(), fetchedResolvedEntity.getReimbursementStatus());
			allResolvedPojo.add(returnReimbursementPojo);
		}

		LOG.info("Exited viewAllResolvedReimbursements() in ManagerServiceImpl...");
		return allResolvedPojo;
	}

	@Override
	public List<ReimbursementPojo> viewIndividualReimbursement(int mgrId, int empId) throws SystemException {
		LOG.info("Entered viewIndividualReimbursement() in ManagerServiceImpl...");
		List<ReimbursementEntity> allIndividualEntity = managerDao.findAllByMgrIdAndEmpId(mgrId, empId);
		List<ReimbursementPojo> allResolvedPojo = new ArrayList<>();
		
		for (ReimbursementEntity fetchedResolvedEntity : allIndividualEntity) {
			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedResolvedEntity.getReimbursementId(), fetchedResolvedEntity.getEmpId(),
					fetchedResolvedEntity.getMgrId(), fetchedResolvedEntity.getReimbursementDesc(),
					fetchedResolvedEntity.getReimbursementAmt(), fetchedResolvedEntity.getReimbursementStatus());
			allResolvedPojo.add(returnReimbursementPojo);
		}

		LOG.info("Exited viewIndividualReimbursement() in ManagerServiceImpl...");

		return allResolvedPojo;
	}
	
//	@EventListener(ApplicationReadyEvent.class)
	@Override
	public boolean approveReimbursement(int empId, int reimbursementId) throws SystemException {
		// 2 step process
//		SimpleMailMessage message = new SimpleMailMessage();

		// 1st - fetch the reimbursentment entoty by specfying the reimburse id
		Optional<ReimbursementEntity> fetchedReimburmentEntity = reimbursementDao.findById(reimbursementId);
		ReimbursementEntity reimEntity = fetchedReimburmentEntity.get();

		// 2nd -change the status of reimbursement to approved and save the
		// reimbursement entity
		reimEntity.setReimbursementStatus("approved");
		reimbursementDao.save(reimEntity);
		
//		message.setFrom("devarianil415@gmail.com");
//		message.setTo("devarianil415@gmail.com");
//		message.setSubject("Subject: Approved Status");
//		message.setText("Body: you're status has been Approved");
//		
//		javaMailSender.send(message);
		
//		sendEmail("devarianil415@gmail.com", "Approved Reimbursement", "Anil kumar, you're Reimbursement has been Approved!");
	
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
	
	@Override
	public EmployeePojo registerAnEmployee(EmployeePojo employeePojo) {
		SimpleMailMessage message = new SimpleMailMessage();
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeePojo, employeeEntity);
		
		//  now pass the bookEntity object to spring data jpa to be updated into the table
		
		int returnedEmployeeEntity = managerDao.insertAttributes(employeeEntity.getMgrId(), employeeEntity.getEmpFirstName(), employeeEntity.getEmpLastName(), employeeEntity.getEmpUserName(), employeeEntity.getEmpPassword());
		
//		EmployeeEntity employeeEntity2 = managerDao.findByEmpId();
//		
//		System.out.println(employeeEntity2);
//
//		BeanUtils.copyProperties(employeeEntity2, employeePojo);
//		
//		System.out.println(employeePojo);
		
		message.setFrom("devarianil415@gmail.com");
		message.setTo(employeePojo.getEmpUserName());
		message.setSubject("Subject: Registration successfull");
		message.setText("Welcome to Expens Reimbursement System!!! you're registered with username: "+ employeePojo.getEmpUserName()+" and password: "+employeePojo.getEmpPassword());
		
		javaMailSender.send(message);
	
		return employeePojo;
	}


	public EmployeePojo individualEmployee(int mgrId, int empId) throws SystemException {

		EmployeeEntity employeeEntity = managerDao.findEmployee(mgrId, empId);
		EmployeePojo employeePojo = new EmployeePojo();

		BeanUtils.copyProperties(employeeEntity, employeePojo);
		return employeePojo;

	}

		public List <ReimbursementPojo> individualEmployeeReimbursement(int mgrId, int empId) throws SystemException, NoRequestException {

		List <ReimbursementEntity> retrievedReimbursement = managerDao.findEmployeeReimb(mgrId, empId);
		List<ReimbursementPojo> returnedEmployeeReimbursements = new ArrayList<>();
		
		if(retrievedReimbursement.isEmpty()) {
			throw new NoRequestException();
		}
		
		for(ReimbursementEntity eachEntity : retrievedReimbursement) {
			
			ReimbursementPojo returnPojo = new ReimbursementPojo(eachEntity.getReimbursementId(),eachEntity.getEmpId(),eachEntity.getMgrId(),eachEntity.getReimbursementDesc(),eachEntity.getReimbursementAmt(),eachEntity.getReimbursementStatus());
			returnedEmployeeReimbursements.add(returnPojo);
		}
	
		return returnedEmployeeReimbursements;

	}

}