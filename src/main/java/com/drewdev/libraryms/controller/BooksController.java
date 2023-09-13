package com.drewdev.libraryms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drewdev.libraryms.dto.DeleteResDto;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.books.BookUpdateReqDto;
import com.drewdev.libraryms.dto.books.BookUpdateStatusReqDto;
import com.drewdev.libraryms.dto.books.BooksInsertReqDto;
import com.drewdev.libraryms.dto.books.BooksResDto;
import com.drewdev.libraryms.service.BooksService;

@RestController
@RequestMapping("books")
public class BooksController {

	@Autowired
	private BooksService booksService;
	
	@GetMapping("/")
	public ResponseEntity<BooksResDto> getById(@RequestParam("id") String id) {
		final BooksResDto book = booksService.getById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
 	
	@GetMapping
	public ResponseEntity<List<BooksResDto>> getAll() {
		final List<BooksResDto> books = booksService.getAll();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody BooksInsertReqDto data) {
		final InsertResDto response = booksService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody BookUpdateReqDto data) {
		final UpdateResDto response = booksService.update(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping("/")
	public ResponseEntity<UpdateResDto> updateStatus(@RequestBody BookUpdateStatusReqDto data) {
		final UpdateResDto response = booksService.updateStatus(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<DeleteResDto> delete(@RequestParam("id") String id) {
		final DeleteResDto response = booksService.delete(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
