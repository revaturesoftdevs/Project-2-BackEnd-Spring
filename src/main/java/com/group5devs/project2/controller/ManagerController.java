package com.group5devs.project2.controller;

import java.util.List;

//import org.graalvm.compiler.hotspot.nodes.profiling.RandomSeedNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5devs.project2.exceptions.SystemException;
import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.pojo.ManagerPojo;
import com.group5devs.project2.pojo.ReimbursementPojo;
import com.group5devs.project2.service.ManagerService;
import com.group5devs.project2.service.ManagerServiceImpl;
@CrossOrigin
@RestController
@RequestMapping("api")
public class ManagerController {
	// final static Logger LOG = LoggerFactory.getLogger(ManagerController.class);
	@Autowired
	ManagerService managerService;
	
//	@Autowired
//	JavaMailSender javaMailSender;

	
	@PostMapping("login-manager")
	public ManagerPojo Login(@RequestBody ManagerPojo managerPojo) throws SystemException {

		return managerService.Login(managerPojo);
	}

	@GetMapping("pending-reimbursements/{mid}")
	public List<ReimbursementPojo> viewAllPendingReimbursements(@PathVariable("mid") int mgrId) throws SystemException {

		// LOG.info("exited View all pending reimbursements method in the controller
		// layer");
		return managerService.viewAllPendingReimbursements(mgrId);

	}

	@GetMapping("resolved-reimbursements/{mid}")
	public List<ReimbursementPojo> viewAllResolvedReimbursements(@PathVariable("mid") int mgrId)
			throws SystemException {

		return managerService.viewAllResolvedReimbursements(mgrId);
	}

	@PutMapping("approve-reimbursement/{eid}/{rid}")
	public boolean approveReimbursement(@PathVariable("eid")int empId,@PathVariable("rid") int reimbursementId) throws SystemException {
		return managerService.approveReimbursement(empId, reimbursementId);
	}
	
	@PutMapping("deny-reimbursement/{eid}/{rid}")
	public boolean denyReimbursement(@PathVariable("eid")int empId,@PathVariable("rid") int reimbursementId) throws SystemException {
			return managerService.denyReimbursement(empId, reimbursementId);
	}
	
	
	@GetMapping("all-employees/{mid}")
	public List<EmployeePojo> viewAllEmployees(@PathVariable("mid")int mgrId) throws SystemException{
		
		return managerService.viewAllEmployees(mgrId);
	}
	

	@PostMapping("register-employee")
	public EmployeePojo registerEmployee(@RequestBody EmployeePojo employeePojo) {
		return managerService.registerAnEmployee(employeePojo);
	}
	
//	@RequestMapping("/sendEmail")
//	public String sendEmail() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		
//		message.setFrom("devarianil415@gmail.com");
//		message.setTo("devarianil415@gmail.com");
//		message.setSubject("Subject: Approved Status");
//		message.setText("Body: you're status has been Approved");
//		
//		javaMailSender.send(message);
//		
//		return "your mail sent successfully!";
//	}
	

	//server.get("/individual-employee/{mid}/{eid}
	@GetMapping("individual-employee/{mid}/{eid}")
	public EmployeePojo individualEmployee(@PathVariable("mid")int mgrId, @PathVariable("eid")int empId) throws SystemException{
		return managerService.individualEmployee(mgrId, empId);
	}
	
	
	//Individual employee reimbursment
	@GetMapping("individual-employee-reimbursement/{mid}/{eid}")
	public List <ReimbursementPojo> findEmployeeReimb(@PathVariable("mid")int mgrId,@PathVariable("eid")int empId)throws SystemException{
		return managerService.individualEmployeeReimbursement(mgrId, empId);
	}

	

}
