package com.group5devs.project2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.group5devs.project2.dao.ManagerDao;
import com.group5devs.project2.entity.ManagerEntity;
import com.group5devs.project2.entity.ReimbursementEntity;
import com.group5devs.project2.exceptions.NoResolvedRequestException;
import com.group5devs.project2.exceptions.SystemException;
import com.group5devs.project2.pojo.ManagerPojo;
import com.group5devs.project2.pojo.ReimbursementPojo;
import com.group5devs.project2.pojo.EmployeePojo;
import com.group5devs.project2.service.ManagerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {
	
	@Mock
	ManagerDao managerDao;
	
	@InjectMocks
	ManagerServiceImpl managerService;
	
	private ManagerPojo dummyManagerPojo;
	private ReimbursementEntity dummyReimbursementEntity;
	
	@BeforeEach
	public void setup() {
		
		dummyManagerPojo = new ManagerPojo(8,"group","five","groupFive@gmail.com","321");
		dummyReimbursementEntity = new ReimbursementEntity(2,3,8,"gas",20.00,"approved");		
		
	}
	
	@DisplayName("JUnit test for viewing resolved reimbursements method")
	@Test
	public void allResolved() throws SystemException, NoResolvedRequestException {
		when(managerDao.findAllResolvedReimbursementStatusAndMgrId(8)).thenReturn(List.of(dummyReimbursementEntity));
		List<ReimbursementPojo> actualAllPendingPojoList = managerService.viewAllResolvedReimbursements(8);
		
		      assertNotNull(actualAllPendingPojoList);
		      assertEquals(1, actualAllPendingPojoList.size());
		
		
	}
	
	
		
}
