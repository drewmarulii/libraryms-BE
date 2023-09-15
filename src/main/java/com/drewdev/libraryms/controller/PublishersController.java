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
import com.drewdev.libraryms.dto.publishers.PublisherInsertMultipleReqDto;
import com.drewdev.libraryms.dto.publishers.PublisherInsertReqDto;
import com.drewdev.libraryms.dto.publishers.PublisherResDto;
import com.drewdev.libraryms.dto.publishers.PublisherUpdateReqDto;
import com.drewdev.libraryms.service.PublishersService;

@RestController
@RequestMapping("publishers")
public class PublishersController {

	@Autowired
	private PublishersService publishersService;
	
	@GetMapping("/")
	public ResponseEntity<PublisherResDto> getById(@RequestParam("id") String id) {
		final PublisherResDto publisher = publishersService.getById(id);
		return new ResponseEntity<>(publisher, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<PublisherResDto>> getAll() {
		final List<PublisherResDto> publishers = publishersService.getAll();
		return new ResponseEntity<>(publishers, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody PublisherInsertReqDto data) {
		final InsertResDto response = publishersService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<InsertResDto> insertMultiple(@RequestBody PublisherInsertMultipleReqDto datas) {
		final InsertResDto response = publishersService.insertMultiple(datas);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody PublisherUpdateReqDto data) {
		final UpdateResDto response = publishersService.update(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<DeleteResDto> delete(@RequestParam("id") String id) {
		final DeleteResDto response = publishersService.delete(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
