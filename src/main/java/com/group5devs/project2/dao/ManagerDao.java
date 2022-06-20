package com.group5devs.project2.dao;
import com.group5devs.project2.pojo.*;
import com.group5devs.project2.entity.EmployeeEntity;
import com.group5devs.project2.entity.ManagerEntity;
import com.group5devs.project2.entity.ReimbursementEntity;
import com.group5devs.project2.exceptions.*;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Component
@Repository
public interface ManagerDao  extends JpaRepository<ManagerEntity, Integer>{
	
	
	@Query("select u from ManagerEntity u where u.mgrUserName=:username and u.mgrPassword=:password")
	ManagerEntity findByMgrUserNameAndMgrPassword(@Param("username")String username, @Param("password")String password);
	
	
	@Query("SELECT u FROM ReimbursementEntity u WHERE u.reimbursementStatus='pending' AND u.mgrId=:mgrId")
	List <ReimbursementEntity> findAllPendingReimbursementStatusAndMgrId(@Param("mgrId")int mgrId);

	@Query("SELECT u FROM ReimbursementEntity u WHERE (u.mgrId=:mgrId) AND (reimbursementStatus='denied' or reimbursementStatus='approved')")
	List<ReimbursementEntity> findAllResolvedReimbursementStatusAndMgrId(@Param("mgrId")int mgrId);

	@Query("select u from ReimbursementEntity u where u.mgrId=:mgrId and u.empId=:empId")
	List<ReimbursementEntity> findAllByMgrIdAndEmpId(@Param("mgrId")int mgrId,@Param("empId")int empId);
//	@Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
//	  User findByLastnameOrFirstname(@Param("lastname") String lastname,
//	                                 @Param("firstname") String firstname);
//	}
	@Query("SELECT u FROM EmployeeEntity u WHERE u.mgrId=:mgrId")
	List<EmployeeEntity> findAllByMgrId(@Param("mgrId")int mgrId);

	@Query("update ReimbursementEntity set reimbursment_status = 'approved' where emp_id =:empId AND reimbursment_id=:reimbursementId")
	boolean approveReimbursement(@Param("empId") int empId, @Param("reimbursementId")int reimbursementId); 
	
	@Transactional
	@Modifying
	@Query(value = "insert into employee_details(mgr_Id,emp_firstname,emp_lastname,emp_username,emp_password) VALUES (:mgrId,:empFirstName,:empLastName,:empUserName,:empPassword)", nativeQuery = true)
	int insertAttributes(@Param("mgrId") int mgrId, @Param("empFirstName") String empFirstName, @Param("empLastName") String empLastName, @Param("empUserName") String empUserName, @Param("empPassword") String empPassword);
	
	@Query(value = "select * from employee_details where emp_id=(select max(emp_id) from employee_details)", nativeQuery = true)
	EmployeeEntity findByEmpId();
	
	
	
	
//	ManagerPojo Login(ManagerPojo managerPojo) throws SystemException;
//	
//	List<ReimbursementPojo> viewAllPendingReimbursements(int mgrId) throws SystemException;
//	
//	List<ReimbursementPojo> viewAllResolvedReimbursements(int mgrId) throws SystemException;
//	
//	List<ReimbursementPojo> viewIndividualReimbursement(int mgrId, int empId) throws SystemException;
//	
//	List<EmployeePojo> viewAllEmployees(int mgrId) throws SystemException;
//	
//	boolean approveReimbursement(int empId,int reimbursementId) throws SystemException;
//	
//	boolean denyReimbursement(int empId,int reimbursementId) throws SystemException;

}