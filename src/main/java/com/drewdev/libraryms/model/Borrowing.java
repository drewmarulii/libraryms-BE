package com.drewdev.libraryms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_borrowing")
public class Borrowing extends BaseEntity  {

	@OneToOne
	@JoinColumn(name="book_id")
	private Books book;
	
	@Column(name="date_borrow", nullable=false)
	private LocalDateTime dateBorrow;
	
	@Column(name="due_date", nullable=false)
	private LocalDateTime dueDate;

	@Column(name="date_return")
	private LocalDateTime dateReturn;
	
	@OneToOne
	@JoinColumn(name="member_id")
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

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}


}
