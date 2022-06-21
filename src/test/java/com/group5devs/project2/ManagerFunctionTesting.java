package com.group5devs.project2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.group5devs.project2.controller.ManagerController;
import com.group5devs.project2.dao.ManagerDao;
import com.group5devs.project2.entity.ManagerEntity;
import com.group5devs.project2.entity.ReimbursementEntity;
import com.group5devs.project2.exceptions.SystemException;
import com.group5devs.project2.pojo.ManagerPojo;
import com.group5devs.project2.service.ManagerServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ManagerFunctionTesting {
	
	@Mock
	private ManagerDao dao;
	
	@InjectMocks
	private ManagerServiceImpl service;
	
	private ManagerPojo expectedPojo;
	private ManagerPojo sendPojo;
	private ManagerEntity dummyPojo;
	
	@BeforeEach
	public void setup(){
		sendPojo = new ManagerPojo (0,"","","john123","boy123");
		expectedPojo = new ManagerPojo(4,"john","boy","john123","boy123");
		dummyPojo = new ManagerEntity(4,"john","boy","john123","boy123");
		List<ReimbursementEntity> viewPendingReimb = new ArrayList<>();
		
	}
	
	@DisplayName("JUnit test for Manager Login")
	@Test
	public void testLogin() throws SystemException {
		
		when(dao.findByMgrUserNameAndMgrPassword("john123", "boy123")).thenReturn(dummyPojo);
		ManagerPojo actualMgrPojo = service.Login(sendPojo);
		assertEquals(expectedPojo, actualMgrPojo);
	}
	

	

}
