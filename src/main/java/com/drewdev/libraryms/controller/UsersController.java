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
import com.drewdev.libraryms.dto.users.UserInsertReqDto;
import com.drewdev.libraryms.dto.users.UserResDto;
import com.drewdev.libraryms.dto.users.UserUpdateReqDto;
import com.drewdev.libraryms.service.UsersService;

@RestController
@RequestMapping("users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping("/")
	public ResponseEntity<UserResDto> getById(@RequestParam("id") String id) {
		final UserResDto user = usersService.getById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserResDto>> getAll() {
		final List<UserResDto> users = usersService.getAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody UserInsertReqDto data) {
		final InsertResDto response = usersService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody UserUpdateReqDto data) {
		final UpdateResDto response = usersService.update(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<DeleteResDto> delete(@RequestParam("id") String id) {
		final DeleteResDto response = usersService.delete(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
