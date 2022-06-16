package com.group5devs.project2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="manager_details")
public class ManagerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mgr_id")
	private int mgrId;
	
	@Column(name="mgr_firstname")
	private String mgrFirstName;
	
	@Column(name="mgr_lastname")
	private String mgrLastName;
	
	@Column(name="mgr_username")
	private String mgrUserName;
	
	@Column(name="mgr_password")
	private String mgrPassword;

	public ManagerEntity() {
		
	}

	public ManagerEntity(int mgrId, String mgrFirstName, String mgrLastName, String mgrUserName, String mgrPassword) {
		super();
		this.mgrId = mgrId;
		this.mgrFirstName = mgrFirstName;
		this.mgrLastName = mgrLastName;
		this.mgrUserName = mgrUserName;
		this.mgrPassword = mgrPassword;
	}

	public int getMgrId() {
		return mgrId;
	}

	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}

	public String getMgrFirstName() {
		return mgrFirstName;
	}

	public void setMgrFirstName(String mgrFirstName) {
		this.mgrFirstName = mgrFirstName;
	}

	public String getMgrLastName() {
		return mgrLastName;
	}

	public void setMgrLastName(String mgrLastName) {
		this.mgrLastName = mgrLastName;
	}

	public String getMgrUserName() {
		return mgrUserName;
	}

	public void setMgrUserName(String mgrUserName) {
		this.mgrUserName = mgrUserName;
	}

	public String getMgrPassword() {
		return mgrPassword;
	}

	public void setMgrPassword(String mgrPassword) {
		this.mgrPassword = mgrPassword;
	}

	@Override
	public String toString() {
		return "ManagerEntity [mgrId=" + mgrId + ", mgrFirstName=" + mgrFirstName + ", mgrLastName=" + mgrLastName
				+ ", mgrUserName=" + mgrUserName + ", mgrPassword=" + mgrPassword + "]";
	}
	
	
	
	
}
