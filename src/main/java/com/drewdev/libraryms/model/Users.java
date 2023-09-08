package com.drewdev.libraryms.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_users")
public class Users extends BaseEntity  {

	private String userFullname;
	private String userEmail;
	private String userPassword;
	private Roles role;
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
