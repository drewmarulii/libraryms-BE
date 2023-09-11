package com.drewdev.libraryms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_books")
public class Books extends BaseEntity {

	@Column(name="book_isbn", length=15, nullable=false, unique=true)
	private String bookIsbn;
	
	@Column(name="book_title", length=50, nullable=false)
	private String bookTitle;
	
	@Column(name="book_publishdate", nullable=false)
	private String bookPublishDate;
	
	@OneToOne
	@JoinColumn(name="author_id")
	private Authors author;
	
	@OneToOne
	@JoinColumn(name="publisher_id")
	private Publishers publisher;
	
	@OneToOne
	@JoinColumn(name="category_id")
	private Categories category;
	
	@OneToOne
	@JoinColumn(name="status_id")
	private BookStatus status;
	
	@OneToOne
	@JoinColumn(name="file_id")
	private File file;

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookPublishDate() {
		return bookPublishDate;
	}

	public void setBookPublishDate(String bookPublishDate) {
		this.bookPublishDate = bookPublishDate;
	}

	public Authors getAuthor() {
		return author;
	}

	public void setAuthor(Authors author) {
		this.author = author;
	}

	public Publishers getPublisher() {
		return publisher;
	}

	public void setPublisher(Publishers publisher) {
		this.publisher = publisher;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
