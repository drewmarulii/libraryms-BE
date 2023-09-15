package com.drewdev.libraryms.dto.categories;

import java.util.List;

public class CategoriesInsertMultipleReqDto {
	private List<CategoriesInsertReqDto> categories;

	public List<CategoriesInsertReqDto> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoriesInsertReqDto> categories) {
		this.categories = categories;
	}
	
}
