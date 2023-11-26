package lms.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
	
	@Column(name="adminEmail")
    private String adminEmail;
	
	@Column(name="adminPassword")
    private String adminPassword;
	
	@Column(name="adminName")
    private String adminName;
	
	@Column(name="adminPhone")
    private String adminPhone;
	
	@Column(name="adminAddress")
    private String adminAddress;
	
	public Admin() {
		
	}

	public Admin(String adminPassword, String adminEmail, String adminName, String adminPhone,
			String adminAddress) {
		super();
		this.adminPassword = adminPassword;
		this.adminEmail = adminEmail;
		this.adminName = adminName;
		this.adminPhone = adminPhone;
		this.adminAddress = adminAddress;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	
	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	
	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	
	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	
	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminPassword=" + adminPassword + ", adminEmail=" + adminEmail
				+ ", adminName=" + adminName  + ", adminPhone=" + adminPhone + ", adminAddress=" + adminAddress + "]";
	}
	
		
}