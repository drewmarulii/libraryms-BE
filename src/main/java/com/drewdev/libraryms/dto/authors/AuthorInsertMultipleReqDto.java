package com.drewdev.libraryms.dto.authors;

import java.util.List;

public class AuthorInsertMultipleReqDto {
	private List<AuthorInsertReqDto> authors;

	public List<AuthorInsertReqDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorInsertReqDto> authors) {
		this.authors = authors;
	}
	
}
