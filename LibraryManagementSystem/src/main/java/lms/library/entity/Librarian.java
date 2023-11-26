package lms.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="librarian")
public class Librarian {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="librarianEmail")
    private String librarianEmail;
	
	@Column(name="librarianPassword")
    private String librarianPassword;
	
	@Column(name="librarianName")
    private String librarianName;
	
	@Column(name="librarianPhone")
    private String librarianPhone;
	
	@Column(name="librarianAddress")
    private String librarianAddress;

	public Librarian() {
	
	}

	public Librarian(String librarianEmail, String librarianPassword, String librarianName,
			String librarianPhone, String librarianAddress) {
		super();
		this.librarianEmail = librarianEmail;
		this.librarianPassword = librarianPassword;
		this.librarianName = librarianName;
		this.librarianPhone = librarianPhone;
		this.librarianAddress = librarianAddress;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getLibrarianEmail() {
		return librarianEmail;
	}

	public void setLibrarianEmail(String librarianEmail) {
		this.librarianEmail = librarianEmail;
	}

	
	public String getLibrarianPassword() {
		return librarianPassword;
	}

	public void setLibrarianPassword(String librarianPassword) {
		this.librarianPassword = librarianPassword;
	}

	
	public String getLibrarianName() {
		return librarianName;
	}

	public void setLibrarianName(String librarianName) {
		this.librarianName = librarianName;
	}

	
	public String getLibrarianPhone() {
		return librarianPhone;
	}

	public void setLibrarianPhone(String librarianPhone) {
		this.librarianPhone = librarianPhone;
	}
	
	
	public String getLibrarianAddress() {
		return librarianAddress;
	}

	public void setLibrarianAddress(String librarianAddress) {
		this.librarianAddress = librarianAddress;
	}

	@Override
	public String toString() {
		return "Librarian [id=" + id + ", librarianEmail=" + librarianEmail + ", librarianPassword=" + librarianPassword
				+ ", librarianName=" + librarianName + ", librarianPhone=" + librarianPhone + ", librarianAddress="
				+ librarianAddress + "]";
	}

	
	
}