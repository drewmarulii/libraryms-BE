package com.drewdev.libraryms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_book_status")
public class BookStatus extends BaseEntity {

	@Column(name="status_code", length=7, nullable=false, unique=true)
	private String statusCode;
	
	@Column(name="status_name", length=15, nullable=false)
	private String statusName;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
