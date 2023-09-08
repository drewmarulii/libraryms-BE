package com.drewdev.libraryms.dto.authors;

public class AuthorInsertReqDto {
	private String authorCode;
	private String authorName;

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
