package model;

import java.util.Objects;

public class ManagerPojo {
	
	int mgrId;
	String mgrFirstName;
	String mgrLastName;
	String mgrUserName;
	String mgrPassword;
	
	public ManagerPojo() {
		super();
	}

	public ManagerPojo(int mgrId, String mgrFirstName, String mgrLastName, String mgrUserName,
			String mgrPassword) {
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
	public int hashCode() {
		return Objects.hash(mgrFirstName, mgrId, mgrLastName, mgrPassword, mgrUserName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManagerPojo other = (ManagerPojo) obj;
		return Objects.equals(mgrFirstName, other.mgrFirstName) && mgrId == other.mgrId
				&& Objects.equals(mgrLastName, other.mgrLastName) && Objects.equals(mgrPassword, other.mgrPassword)
				&& Objects.equals(mgrUserName, other.mgrUserName);
	}

	@Override
	public String toString() {
		return "ManagerPojo [mgrId=" + mgrId + ", mgrFirstName=" + mgrFirstName + ", mgrLastName=" + mgrLastName
				+ ", mgrUserName=" + mgrUserName + ", mgrPassword=" + mgrPassword + "]";
	}
	
}