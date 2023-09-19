package com.drewdev.libraryms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drewdev.libraryms.dto.DeleteResDto;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.authors.AuthorInsertMultipleReqDto;
import com.drewdev.libraryms.dto.authors.AuthorInsertReqDto;
import com.drewdev.libraryms.dto.authors.AuthorResDto;
import com.drewdev.libraryms.dto.authors.AuthorUpdateReqDto;
import com.drewdev.libraryms.service.AuthorsService;

@RestController
@RequestMapping("authors")
public class AuthorsController {

	@Autowired
	private AuthorsService authorsService;
	
	@GetMapping("/")
	public ResponseEntity<AuthorResDto> getById(@RequestParam("id") String id) {
		final AuthorResDto author = authorsService.getById(id);
		return new ResponseEntity<>(author, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<AuthorResDto>> getAll() {
		final List<AuthorResDto> authors = authorsService.getAll();
		return new ResponseEntity<>(authors, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody AuthorInsertReqDto data) {
		final InsertResDto response = authorsService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<InsertResDto> insertMultiple(@RequestBody AuthorInsertMultipleReqDto datas) {
		final InsertResDto response = authorsService.insertMultiple(datas);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody AuthorUpdateReqDto data) {
		final UpdateResDto response = authorsService.update(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteResDto> delete(@PathVariable("id") String id) {
		final DeleteResDto response = authorsService.delete(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
