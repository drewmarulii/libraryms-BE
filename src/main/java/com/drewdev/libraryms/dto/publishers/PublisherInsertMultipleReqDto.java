package com.drewdev.libraryms.dto.publishers;

import java.util.List;

public class PublisherInsertMultipleReqDto {
	private List<PublisherInsertReqDto> publishers;

	public List<PublisherInsertReqDto> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<PublisherInsertReqDto> publishers) {
		this.publishers = publishers;
	}

}
