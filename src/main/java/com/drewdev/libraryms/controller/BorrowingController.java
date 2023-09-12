package com.drewdev.libraryms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.borrowing.BorrowingInsertReqDto;
import com.drewdev.libraryms.dto.borrowing.BorrowingResDto;
import com.drewdev.libraryms.dto.borrowing.BorrowingUpdateReqDto;
import com.drewdev.libraryms.service.BorrowingService;

@RestController
@RequestMapping("borrowings")
public class BorrowingController {

	@Autowired
	private BorrowingService borrowingService;
	
	@GetMapping
	public ResponseEntity<List<BorrowingResDto>> getAll() {
		final List<BorrowingResDto> borrowings = borrowingService.getAll();
		return new ResponseEntity<>(borrowings, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<BorrowingResDto> getById(@RequestParam("id") String id) {
		final BorrowingResDto borrowing = borrowingService.getById(id);
		return new ResponseEntity<>(borrowing, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody BorrowingInsertReqDto data) {
		final InsertResDto response = borrowingService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody BorrowingUpdateReqDto data) {
		final UpdateResDto response = borrowingService.updateReturnBook(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
