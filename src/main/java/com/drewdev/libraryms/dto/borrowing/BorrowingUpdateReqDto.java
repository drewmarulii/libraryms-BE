package com.drewdev.libraryms.dto.borrowing;

import java.time.LocalDateTime;

public class BorrowingUpdateReqDto {
	private String id;
	private LocalDateTime dateReturn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(LocalDateTime dateReturn) {
		this.dateReturn = dateReturn;
	}

}
