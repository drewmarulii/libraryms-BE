package com.drewdev.libraryms.dto.books;

import java.util.List;

public class BooksInsertMultipleReqDto {
	private List<BooksInsertReqDto> books;

	public List<BooksInsertReqDto> getBooks() {
		return books;
	}

	public void setBooks(List<BooksInsertReqDto> books) {
		this.books = books;
	}
	
}
