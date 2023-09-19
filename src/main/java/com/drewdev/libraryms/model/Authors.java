package com.drewdev.libraryms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "m_authors")
@Getter @Setter @NoArgsConstructor
public class Authors extends BaseEntity implements Serializable {

	@Column(name="author_code", length=7, nullable=false, unique=true)
	private String authorCode;
	
	@Column(name="author_name", length=15, nullable=false)
	private String authorName;
}
