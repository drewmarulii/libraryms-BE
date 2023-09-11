package com.drewdev.libraryms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drewdev.libraryms.dto.books.BooksResDto;
import com.drewdev.libraryms.service.BooksService;

@RestController
@RequestMapping("books")
public class BooksController {

	@Autowired
	private BooksService booksService;
	
	@GetMapping
	public ResponseEntity<List<BooksResDto>> getAll() {
		final List<BooksResDto> books = booksService.getAll();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
}
