package com.group5devs.project2;

//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.Module.SetupContext;
import com.group5devs.project2.controller.EmployeeController;
import com.group5devs.project2.dao.EmployeeDao;
import com.group5devs.project2.dao.ReimbursementDao;
import com.group5devs.project2.entity.EmployeeEntity;
import com.group5devs.project2.entity.ReimbursementEntity;
import com.group5devs.project2.exceptions.NoPendingRequestException;
import com.group5devs.project2.exceptions.NoResolvedRequestException;
import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.pojo.ReimbursementPojo;
import com.group5devs.project2.service.EmployeeServiceImpl;


@ExtendWith(MockitoExtension.class)
class Project2ApplicationTests {
	
    
//    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
	
	@Mock
    EmployeeDao employeeDao;
	
	@Mock
	ReimbursementDao reimbursementDao;

	//@InjectMocks will inject the mocks marked with @Mock to this instance when it is created.
    @InjectMocks
    EmployeeServiceImpl service;
	
    
    private EmployeePojo expectedEmployeePojo;
    private EmployeeEntity dummyEmployeeEntity;
    private ReimbursementEntity dummyReimbursementEntity;
    private ReimbursementEntity dummyReimbursementEntityTwo;
    private ReimbursementPojo sendReimbursementPojo;
    private ReimbursementPojo expectedReimbursementPojo;
    
    @BeforeEach
    public void setup() {
    	expectedEmployeePojo = new EmployeePojo(1, 2, "john", "jean", "john jean", "option");
    	dummyEmployeeEntity = new EmployeeEntity(1, 2, "john", "jean", "john jean", "option");
    	dummyReimbursementEntityTwo = new ReimbursementEntity(3, 4, 3, "beam", 100.00, "approved");
    	
    	dummyReimbursementEntity = new ReimbursementEntity(2, 3, 3, "name", 3.5, "dummy");
    	expectedReimbursementPojo = new ReimbursementPojo(2, 3, 3, "name", 3.5, "dummy");
    }
    
    @DisplayName("JUnit test for loginEmployee method")
	@Test
    public void testLoginEmployee() {
    	when(employeeDao.findByEmpUserNameAndEmpPassword("john jean", "option")).thenReturn(dummyEmployeeEntity);
    	EmployeePojo sendLoginEmployeePojo = new EmployeePojo(0, 0, "", "", "john jean", "option");
    	EmployeePojo actualLoginEmployeePojo = service.login(sendLoginEmployeePojo);
    	assertEquals(expectedEmployeePojo, actualLoginEmployeePojo);
    }
    
    @DisplayName("JUnit test for viewEmployee method")
	@Test
	public void testViewEmployee() {
		
		when(employeeDao.findById(1)).thenReturn(Optional.of(dummyEmployeeEntity));
		EmployeePojo actualEmployeePojo = service.viewEmployeeProfile(1);
		assertEquals(expectedEmployeePojo, actualEmployeePojo);
		
	}
    
    @DisplayName("JUnit test for modifyEmployee method")
    @Test
    public void testModifyEmployee() {
    	
    	when(employeeDao.save(dummyEmployeeEntity)).thenReturn(dummyEmployeeEntity);
    	EmployeePojo sendEmployeePojo = new EmployeePojo(1, 2, "john", "jean", "john jean", "option");
    	EmployeePojo actualEmployeePojo = service.modifyEmployee(sendEmployeePojo);
    	
    	assertEquals(expectedEmployeePojo, actualEmployeePojo);
    }
    
    @DisplayName("JUnit test for viewPendingReimbursements method")
    @Test
    public void testViewPendingReimbursements() throws NoPendingRequestException{
    	when(reimbursementDao.findByEmpId(1)).thenReturn(List.of(dummyReimbursementEntity, dummyReimbursementEntity));
    	
    	List<ReimbursementPojo> actualAllPendingReimbursementPojoList = service.viewPendingReimbursements(1);
    	
    	assertNotNull(actualAllPendingReimbursementPojoList);
    	assertEquals(2, actualAllPendingReimbursementPojoList.size());
    }
    
    
    @DisplayName("JUnit test for viewResolvedReimbursements method")
    @Test
    public void testViewResolvedReimbursements() throws NoResolvedRequestException{
    	
//    	mockito.strictness(Strictness.LENIENT);
//    	List<ReimbursementEntity> listReimbursementEntities = (List<ReimbursementEntity>) new ReimbursementEntity();
//    	listReimbursementEntities.add(dummyReimbursementEntity);
//    	listReimbursementEntities.add(dummyReimbursementEntity);
//    	when(reimbursementDao.findByEmpId(3)).thenReturn(listReimbursementEntities);
    	doReturn(List.of(dummyReimbursementEntityTwo, dummyReimbursementEntityTwo, dummyReimbursementEntityTwo)).when(reimbursementDao.findByEmpId(4));
    	
    	List<ReimbursementPojo> actualAllResolvedReimbursementPojoList = service.viewResolvedReimbursements((short) 4);
    	
    	assertNotNull(actualAllResolvedReimbursementPojoList);
    	assertEquals(3, actualAllResolvedReimbursementPojoList.size());
    }
    
    @DisplayName("JUnit test for addReimbursement method")
    @Test
    public void testAddReimbursment() {
//    	when(reimbursementDao.saveAndFlush(dummyReimbursementEntity)).thenReturn(dummyReimbursementEntity);
    	doReturn(dummyReimbursementEntity).when(reimbursementDao.saveAndFlush(dummyReimbursementEntity));
    	
    	ReimbursementPojo sendReimbursementPojo = new ReimbursementPojo(2, 3, 3, "name", 3.5, "dummy");
    	ReimbursementPojo actualReimbursementPojo = service.addReimbursment(sendReimbursementPojo);
    	
    	assertEquals(expectedReimbursementPojo, actualReimbursementPojo);
    }

	

}
