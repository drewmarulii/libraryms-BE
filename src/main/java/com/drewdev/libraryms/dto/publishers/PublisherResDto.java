package com.drewdev.libraryms.dto.publishers;

public class PublisherResDto {
	private String id;
	private String publName;
	private String publAddress;
	private String publPhone;
	private String publFax;
	private String publEmail;
	private String isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
