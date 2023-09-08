package com.drewdev.libraryms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_borrowing")
public class Borrowing {

	private Books book;
	private LocalDateTime dateBorrow;
	private LocalDateTime dateReturn;
	private Members member;

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
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

	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}

}
