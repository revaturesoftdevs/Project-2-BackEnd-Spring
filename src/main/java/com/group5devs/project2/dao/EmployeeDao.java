package com.group5devs.project2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group5devs.project2.entity.EmployeeEntity;
import com.group5devs.project2.pojo.EmployeePojo;






@Repository
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Integer>{
	
//	String findByEmpUserName(String empUserName);
//	String findByEmpPassword(String empPassword);
	
	@Query("From EmployeeEntity WHERE empUserName = ?1 AND empPassword = ?2")
	  EmployeeEntity findByEmpUserNameAndEmpPassword(String empUserName, String empPassword);
	}
	

