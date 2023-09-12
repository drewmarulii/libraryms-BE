package com.drewdev.libraryms.dto.book_attachments;

import java.util.List;

import com.drewdev.libraryms.dto.files.FileInsertReqDto;

public class BookAttachmentsInsertReqDto {
	private String bookId;
	private List<FileInsertReqDto> files;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public List<FileInsertReqDto> getFiles() {
		return files;
	}

	public void setFiles(List<FileInsertReqDto> files) {
		this.files = files;
	}
	
}
