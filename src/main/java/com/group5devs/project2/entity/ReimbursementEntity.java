package com.group5devs.project2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reimbursment_details")
public class ReimbursementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reimbursment_id")
	private int reimbursementId;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="mgr_id")
	private int mgrId;
	
	@Column(name="reimbursment_desc")
	private String reimbursementDesc;
	
	@Column(name="reimbursment_amt")
	private Double reimbursementAmt;
	
	@Column(name="reimbursment_status")
	private String reimbursementStatus;

	public ReimbursementEntity() {

	}
	
	
	
	public ReimbursementEntity(int empId, int mgrId, String reimbursementDesc, Double reimbursementAmt,
			String reimbursementStatus) {
		super();
		this.empId = empId;
		this.mgrId = mgrId;
		this.reimbursementDesc = reimbursementDesc;
		this.reimbursementAmt = reimbursementAmt;
		this.reimbursementStatus = reimbursementStatus;
	}



	public ReimbursementEntity(int reimbursementId, int empId, int mgrId, String reimbursementDesc,
			Double reimbursementAmt, String reimbursementStatus) {
		super();
		this.reimbursementId = reimbursementId;
		this.empId = empId;
		this.mgrId = mgrId;
		this.reimbursementDesc = reimbursementDesc;
		this.reimbursementAmt = reimbursementAmt;
		this.reimbursementStatus = reimbursementStatus;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
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

	public String getReimbursementDesc() {
		return reimbursementDesc;
	}

	public void setReimbursementDesc(String reimbursementDesc) {
		this.reimbursementDesc = reimbursementDesc;
	}

	public Double getReimbursementAmt() {
		return reimbursementAmt;
	}

	public void setReimbursementAmt(Double reimbursementAmt) {
		this.reimbursementAmt = reimbursementAmt;
	}

	public String getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}

	@Override
	public String toString() {
		return "ReimbursementEntity [reimbursementId=" + reimbursementId + ", empId=" + empId + ", mgrId=" + mgrId
				+ ", reimbursementDesc=" + reimbursementDesc + ", reimbursementAmt=" + reimbursementAmt
				+ ", reimbursementStatus=" + reimbursementStatus + "]";
	}
	
	
	
	
	
	
	

	
	
	
	
	
}
