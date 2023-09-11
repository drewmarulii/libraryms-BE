package com.drewdev.libraryms.dto.authors;

public class AuthorUpdateReqDto {
	private String id;
	private String authorName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
