package com.drewdev.libraryms.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_book_attachments")
public class BookAttachments extends BaseEntity {

	private Books bookId;
	private File fileId;

	public Books getBookId() {
		return bookId;
	}

	public void setBookId(Books bookId) {
		this.bookId = bookId;
	}

	public File getFileId() {
		return fileId;
	}

	public void setFileId(File fileId) {
		this.fileId = fileId;
	}

}
