package com.drewdev.libraryms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drewdev.libraryms.dto.book_status.BookStatusResDto;
import com.drewdev.libraryms.service.BookStatusService;

@RestController
@RequestMapping("bookstatus")
public class BookStatusController {
	
	@Autowired
	private BookStatusService bookStatusService;
	
	@GetMapping
	public ResponseEntity<List<BookStatusResDto>> getAll() {
		final List<BookStatusResDto> statuses = bookStatusService.getAll();
		return new ResponseEntity<>(statuses, HttpStatus.OK);
	}
}
