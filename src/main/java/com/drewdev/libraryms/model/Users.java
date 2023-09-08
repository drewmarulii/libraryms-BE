package com.drewdev.libraryms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_users")
public class Users extends BaseEntity {

	@Column(name="user_fullname", length=30, nullable=false)
	private String userFullname;
	
	@Column(name="user_email", length=50, nullable=false, unique=true)
	private String userEmail;
	
	@Column(name="user_password", nullable=false)
	private String userPassword;
	
	@OneToOne
	@JoinColumn(name="role_id")
	private Roles role;
	
	@OneToOne
	@JoinColumn(name="file_id")
	private File file;

	public String getUserFullname() {
		return userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
