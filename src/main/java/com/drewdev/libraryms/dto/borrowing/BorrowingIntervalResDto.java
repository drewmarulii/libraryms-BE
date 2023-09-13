package com.drewdev.libraryms.dto.borrowing;

import java.time.LocalDateTime;

public class BorrowingIntervalResDto {
	private String id;
	private String bookId;
	private LocalDateTime dateBorrow;
	private LocalDateTime dateDue;
	private LocalDateTime dateReturn;
	private String memberId;
	private String interval;

	public LocalDateTime getDateDue() {
		return dateDue;
	}

	public void setDateDue(LocalDateTime dateDue) {
		this.dateDue = dateDue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDateTime getDateBorrow() {
		return dateBorrow;
	}

	public void setDateBorrow(LocalDateTime dateBorrow) {
		this.dateBorrow = dateBorrow;
	}

	public LocalDateTime getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(LocalDateTime dateReturn) {
		this.dateReturn = dateReturn;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

}
