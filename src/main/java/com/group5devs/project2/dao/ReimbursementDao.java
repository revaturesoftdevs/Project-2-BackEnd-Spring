package com.group5devs.project2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group5devs.project2.entity.EmployeeEntity;
import com.group5devs.project2.entity.ReimbursementEntity;

@Repository
public interface ReimbursementDao extends JpaRepository<ReimbursementEntity, Integer>{
	
	@Query(value = "SELECT * FROM reimbursment_details WHERE emp_id = ?1 AND reimbursment_status = 'pending'", nativeQuery = true)
	List<ReimbursementEntity> findByEmpId(int empId);
	
	@Query(value = "SELECT * FROM reimbursment_details WHERE (emp_id = ?1) AND (reimbursment_status = 'approved' OR reimbursment_status = 'denied')", nativeQuery = true)
	List<ReimbursementEntity> findByEmpId(short empId);
	
//	List<ReimbursementEntity> findByReimbursementStatusAndEmpId(String status, int empId);
	
//	List<BookEntity>findByBookGenre(String genre);
//	List<BookEntity> findByBookGenreAndAuthor(String genre, String author);
//	@GetMapping("books/{bid}")
//	public List<BookPojo> getBooksByGenre(@PathVariable("Genre") String genre) throws ApplicationException {
//		return null;
//	}




}
