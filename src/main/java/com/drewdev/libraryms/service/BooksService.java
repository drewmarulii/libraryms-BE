package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.dao.BooksDao;
import com.drewdev.libraryms.dto.books.BooksResDto;
import com.drewdev.libraryms.model.Books;

@Service
public class BooksService {
	
	@Autowired
	private BooksDao booksDao;
	
	public List<BooksResDto> getAll() {
		final List<Books> books = booksDao.getAll(Books.class);
		final List<BooksResDto> booksRes = new ArrayList<>();
		
		for(int i=0; i<books.size(); i++) {
			final BooksResDto book = new BooksResDto();
			book.setId(books.get(i).getId());
			book.setBookIsbn(books.get(i).getBookIsbn());
			book.setBookTitle(books.get(i).getBookTitle());
			book.setBookPublishdate(books.get(i).getBookPublishDate());
			book.setAuthorId(books.get(i).getAuthor().getId());
			book.setPublisherId(books.get(i).getPublisher().getId());
			book.setCategoryId(books.get(i).getCategory().getId());
			book.setStatusId(books.get(i).getStatus().getId());
			book.setFileId(books.get(i).getFile().getId());
			booksRes.add(book);
		}
		
		return booksRes;
	}
}
