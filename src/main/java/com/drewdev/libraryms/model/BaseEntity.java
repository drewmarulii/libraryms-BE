package com.drewdev.libraryms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.persistence.GenerationType;

@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created_by",  nullable = false)
	private Long createdBy;
	
	@Column(name = "created_at",  nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "is_active",  nullable = false)
	private Boolean isActive;
	
	@Version
	private Integer version;
	
	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.isActive = true;
	}
	
	@PreUpdate
	private void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Long getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
