package com.drewdev.libraryms.dto.books;

public class BookUpdateStatusReqDto {
	private String id;
	private String statusId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

}
