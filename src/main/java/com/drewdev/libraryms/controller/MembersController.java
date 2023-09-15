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
import com.drewdev.libraryms.dto.members.MemberInsertMultipleReqDto;
import com.drewdev.libraryms.dto.members.MemberInsertReqDto;
import com.drewdev.libraryms.dto.members.MemberResDto;
import com.drewdev.libraryms.dto.members.MemberUpdateReqDto;
import com.drewdev.libraryms.service.MembersService;

@RestController
@RequestMapping("members")
public class MembersController {

	@Autowired
	private MembersService membersService;
	
	@GetMapping("/")
	public ResponseEntity<MemberResDto> getById(@RequestParam("id") String id) {
		final MemberResDto member = membersService.getById(id);
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<MemberResDto>> getAll() {
		final List<MemberResDto> members = membersService.getAll();
		return new ResponseEntity<>(members, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody MemberInsertReqDto data) {
		final InsertResDto response = membersService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<InsertResDto> insertMultiple(@RequestBody MemberInsertMultipleReqDto datas) {
		final InsertResDto response = membersService.insertMultiple(datas);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody MemberUpdateReqDto data) {
		final UpdateResDto response = membersService.update(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<DeleteResDto> delete(@RequestParam("id") String id) {
		final DeleteResDto response = membersService.delete(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
