package com.drewdev.libraryms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_authors")
public class Authors extends BaseEntity {

	@Column(name="author_code", length=7, nullable=false, unique=true)
	private String authorCode;
	
	@Column(name="author_name", length=15, nullable=false)
	private String authorName;

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
