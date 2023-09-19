package com.drewdev.libraryms.dto.borrowing;

import java.util.List;

public class BorrowingInsertReqDto {
	private String memberId;
	private List<String> bookId;

	public List<String> getBookId() {
		return bookId;
	}

	public void setBookId(List<String> bookId) {
		this.bookId = bookId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
