package com.drewdev.libraryms.dto.categories;

public class CategoriesUpdateReqDto {
	private String id;
	private String categoryName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
