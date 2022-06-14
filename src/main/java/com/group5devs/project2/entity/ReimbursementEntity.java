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
	@Column(name="reimbursementId")
	private int reimbursementId;
	
	@Column(name="empId")
	private int empId;
	
	@Column(name="mgrId")
	private int mgrId;
	
	@Column(name="reimburementDesc")
	private String empFirstName;
	
	@Column(name="reimbursementAmt")
	private String empLastName;
	
	@Column(name="reimbursementStatus")
	private String empUserName;

	public ReimbursementEntity() {

	}

	public ReimbursementEntity(int reimbursementId, int empId, int mgrId, String empFirstName, String empLastName,
			String empUserName) {
		super();
		this.reimbursementId = reimbursementId;
		this.empId = empId;
		this.mgrId = mgrId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empUserName = empUserName;
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

	@Override
	public String toString() {
		return "ReimbursementEntity [reimbursementId=" + reimbursementId + ", empId=" + empId + ", mgrId=" + mgrId
				+ ", empFirstName=" + empFirstName + ", empLastName=" + empLastName + ", empUserName=" + empUserName
				+ "]";
	}
	
	
	
	
}
