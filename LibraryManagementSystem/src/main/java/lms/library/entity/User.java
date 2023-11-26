package lms.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="user")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="userName")
    private String userName;
	
	@Column(name="userEmail")
    private String userEmail;
	
	@Column(name="userPhone")
    private String userPhone;
	
	@Column(name="userAddress")
    private String userAddress;
	
	public User() {
		
	}

	public User(Long id, String userName, String userEmail, String userPhone, String userAddress) {
		super();
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userEmail=" + userEmail + ", userPhone=" + userPhone
				+ ", userAddress=" + userAddress + "]";
	}

	
}