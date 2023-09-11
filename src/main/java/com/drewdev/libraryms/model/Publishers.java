package com.drewdev.libraryms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_publishers")
public class Publishers extends BaseEntity {

	@Column(name="publ_name", length=50, nullable=false)
	private String publName;
	
	@Column(name="publ_address", nullable=false)
	private String publAddress;
	
	@Column(name="publ_phone", length=15, nullable=false, unique=true)
	private String publPhone;
	
	@Column(name="publ_fax", length=15, unique=true)
	private String publFax;
	
	@Column(name="publ_email",length=50, nullable=false, unique=true)
	private String publEmail;

	public String getPublName() {
		return publName;
	}

	public void setPublName(String publName) {
		this.publName = publName;
	}

	public String getPublAddress() {
		return publAddress;
	}

	public void setPublAddress(String publAddress) {
		this.publAddress = publAddress;
	}

	public String getPublPhone() {
		return publPhone;
	}

	public void setPublPhone(String publPhone) {
		this.publPhone = publPhone;
	}

	public String getPublFax() {
		return publFax;
	}

	public void setPublFax(String publFax) {
		this.publFax = publFax;
	}

	public String getPublEmail() {
		return publEmail;
	}

	public void setPublEmail(String publEmail) {
		this.publEmail = publEmail;
	}

}
