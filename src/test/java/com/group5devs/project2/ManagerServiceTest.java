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
	public void allResolved() throws SystemException {
		when(managerDao.findAllResolvedReimbursementStatusAndMgrId(8)).thenReturn(List.of(dummyReimbursementEntity));
		List<ReimbursementPojo> actualAllPendingPojoList = managerService.viewAllResolvedReimbursements(8);
		
		      assertNotNull(actualAllPendingPojoList);
		      assertEquals(1, actualAllPendingPojoList.size());
		
		
	}
	
	
		
}
//@ExtendWith(MockitoExtension.class)
//public class BooKServiceTest {
//	
//	@Mock
//	BookDao bookDao;
//	
//	@InjectMocks
//	BookServiceImpl bookService;
//	
//	private BookPojo expectedBookPojo;
//	private BookEntity dummyBookEntity;
//	
//	@BeforeEach
//  public void setup(){
//      
//      expectedBookPojo = new BookPojo(1, "Flying Dragons", "Comedy", "Geronimo Stilton", 20, "");
//      dummyBookEntity = new BookEntity(1, "Flying Dragons", "Comedy", "Geronimo Stilton", 20, "");
//      
//  }
//
//  // JUnit test for save Book method
//  @DisplayName("JUnit test for save book method")
//  @Test
//  public void testAddBook() throws ApplicationException{
//     when(bookDao.saveAndFlush(dummyBookEntity)).thenReturn(dummyBookEntity);
//
//     BookPojo sendBookPojo = new BookPojo(1, "Flying Dragons", "Comedy", "Geronimo Stilton", 20, "");
//     BookPojo actualBookPojo = bookService.addBook(sendBookPojo);
//
//     assertEquals(1, actualBookPojo.getId());
//  }
//
//  // JUnit test for getAllBooks method
//  @DisplayName("JUnit test for getAllBooks method")
//  @Test
//  public void testGetAllBooks() throws ApplicationException, BookEmptyException{
//      when(bookDao.findAll()).thenReturn(List.of(dummyBookEntity, dummyBookEntity));
// 
//      List<BookPojo> actualAllBookPojoList = bookService.getAllBooks();
//
//      assertNotNull(actualAllBookPojoList);
//      assertEquals(2, actualAllBookPojoList.size());
//  }
//  
//  // JUnit test for getABook method
//  @DisplayName("JUnit test for getABook method")
//  @Test
//  public void testGetABook() throws ApplicationException, BookNotFoundException{
//  	when(bookDao.findById(1)).thenReturn(Optional.of(dummyBookEntity));
//  	BookPojo actualBookPojo = bookService.getABook(1);
//  	assertEquals(expectedBookPojo, actualBookPojo);
//  }
//  
//  // JUnit test for deleteBook method
//  @DisplayName("JUnit test for deleteBook method")
//  @Test
//  public void testDeleteBook() throws ApplicationException, BookNotFoundException{
//  	doNothing().when(bookDao).deleteById(1);
//  	bookService.deleteBook(1);
//  	verify(bookDao, times(1)).deleteById(1);
//  }
//  
//  // JUnit test for save Book method
//  @DisplayName("JUnit test for update book method")
//  @Test
//  public void testUpdateBook() throws ApplicationException{
//     when(bookDao.save(dummyBookEntity)).thenReturn(dummyBookEntity);
//
//     BookPojo sendBookPojo = new BookPojo(1, "Flying Dragons", "Comedy", "Geronimo Stilton", 20, "");
//     BookPojo actualBookPojo = bookService.updateBook(sendBookPojo);
//
//     assertEquals(expectedBookPojo, actualBookPojo);
//  }
//
//}