package com.drewdev.libraryms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.book_attachments.BookAttachmentResDto;
import com.drewdev.libraryms.dto.book_attachments.BookAttachmentsInsertReqDto;
import com.drewdev.libraryms.service.BookAttachmentsService;

@RestController
@RequestMapping("attachments")
public class BookAttachmentsController {

	@Autowired
	private BookAttachmentsService bookAttachmentsService;
	
	@GetMapping("/")
	public ResponseEntity<List<BookAttachmentResDto>> getByBookId(@RequestParam("id") String id) {
		final List<BookAttachmentResDto> attachments = bookAttachmentsService.getByBookId(id);
		return new ResponseEntity<>(attachments, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody BookAttachmentsInsertReqDto data) {
		final InsertResDto response = bookAttachmentsService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
