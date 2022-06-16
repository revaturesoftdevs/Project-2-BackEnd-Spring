package com.group5devs.project2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_details")
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="mgr_id")
	private int mgrId;
	
	@Column(name="emp_firstname")
	private String empFirstName;
	
	@Column(name="emp_lastname")
	private String empLastName;
	
	@Column(name="emp_username")
	private String empUserName;
	
	@Column(name="emp_password")
	private String empPassword;
	
	public EmployeeEntity() {
		
	}

	public EmployeeEntity(int empId, int mgrId, String empFirstName, String empLastName, String empUserName,
			String empPassword) {
		super();
		this.empId = empId;
		this.mgrId = mgrId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empUserName = empUserName;
		this.empPassword = empPassword;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getMgrId() {
		return mgrId;
	}

	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpUserName() {
		return empUserName;
	}

	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [empId=" + empId + ", mgrId=" + mgrId + ", empFirstName=" + empFirstName
				+ ", empLastName=" + empLastName + ", empUserName=" + empUserName + ", empPassword=" + empPassword
				+ "]";
	}
	
	

}
