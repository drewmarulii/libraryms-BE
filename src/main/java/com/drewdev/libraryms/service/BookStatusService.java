package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.dao.BookStatusDao;
import com.drewdev.libraryms.dto.book_status.BookStatusResDto;
import com.drewdev.libraryms.model.BookStatus;

@Service
public class BookStatusService {

	@Autowired
	private BookStatusDao bookStatusDao;
	
	public List<BookStatusResDto> getAll() {
		final List<BookStatus> bookStatuses = bookStatusDao.getAll(BookStatus.class);
		final List<BookStatusResDto> bookStatusRes = new ArrayList<>();
		
		for(int i=0; i<bookStatuses.size(); i++) {
			final BookStatusResDto bookStatus = new BookStatusResDto();
			bookStatus.setId(bookStatuses.get(i).getId());
			bookStatus.setStatusCode(bookStatuses.get(i).getStatusCode());
			bookStatus.setStatusName(bookStatuses.get(i).getStatusName());
			bookStatus.setIsActive(Boolean.valueOf(bookStatuses.get(i).getIsActive()).toString());
			bookStatusRes.add(bookStatus);
		}
		
		return bookStatusRes;
	}
}
