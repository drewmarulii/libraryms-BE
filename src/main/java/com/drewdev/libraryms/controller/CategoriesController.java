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
import com.drewdev.libraryms.dto.categories.CategoriesInsertMultipleReqDto;
import com.drewdev.libraryms.dto.categories.CategoriesInsertReqDto;
import com.drewdev.libraryms.dto.categories.CategoriesResDto;
import com.drewdev.libraryms.dto.categories.CategoriesUpdateReqDto;
import com.drewdev.libraryms.service.CategoriesService;

@RestController
@RequestMapping("categories")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;
	
	@GetMapping("/")
	public ResponseEntity<CategoriesResDto> getById(@RequestParam("id") String id) {
		final CategoriesResDto category = categoriesService.getById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriesResDto>> getAll() {
		final List<CategoriesResDto> categories = categoriesService.getAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody CategoriesInsertReqDto data) {
		final InsertResDto response = categoriesService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<InsertResDto> insertMultiple(@RequestBody CategoriesInsertMultipleReqDto datas) {
		final InsertResDto response = categoriesService.insertMultiple(datas);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody CategoriesUpdateReqDto data) {
		final UpdateResDto response = categoriesService.update(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteResDto> delete(@PathVariable("id") String id) {
		final DeleteResDto response = categoriesService.delete(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
