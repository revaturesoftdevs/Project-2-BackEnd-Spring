package model;

import java.util.Objects;

public class EmployeePojo {
	
	//testing
	int empId; 
	int mgrId;
	String empFirstName;
	String empLastName;
	String empUserName;
	String empPassword;
	


	public EmployeePojo() {
		super();
	}

	public EmployeePojo(int empId, int mgrId, String empFirstName, String empLastName, String empUserName,
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
	public int hashCode() {
		return Objects.hash(empFirstName, empId, empLastName, empPassword, empUserName, mgrId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePojo other = (EmployeePojo) obj;
		return Objects.equals(empFirstName, other.empFirstName) && empId == other.empId
				&& Objects.equals(empLastName, other.empLastName) && Objects.equals(empPassword, other.empPassword)
				&& Objects.equals(empUserName, other.empUserName) && mgrId == other.mgrId;
	}

	@Override
	public String toString() {
		return "EmployeePojo [empId=" + empId + ", mgrId=" + mgrId + ", empFirstName=" + empFirstName
				+ ", empLastName=" + empLastName + ", empUserName=" + empUserName + ", empPassword=" + empPassword
				+ "]";
	}
	
	
	
	
}
