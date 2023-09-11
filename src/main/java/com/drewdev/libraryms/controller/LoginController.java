package com.drewdev.libraryms.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drewdev.libraryms.dto.login.LoginReqDto;
import com.drewdev.libraryms.dto.login.LoginResDto;
import com.drewdev.libraryms.service.JwtService;
import com.drewdev.libraryms.service.UsersService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("login")
@SecurityRequirement(name = "bearerAuth")
public class LoginController {

	private final UsersService usersService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public LoginController(UsersService usersService, AuthenticationManager authenticationManager, JwtService jwtService) {
		this.usersService = usersService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@PostMapping
	public ResponseEntity<?> login(@RequestBody final LoginReqDto user) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword());

		authenticationManager.authenticate(auth);
		final LoginResDto userLogin = usersService.login(user);

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);

		final Map<String, Object> claims = new HashMap<>();
		claims.put("exp", cal.getTime());
		claims.put("id", userLogin.getId());

		final LoginResDto loginRes = new LoginResDto();
		loginRes.setId(userLogin.getId());
		loginRes.setFullname(userLogin.getFullname());
		loginRes.setRoleId(userLogin.getRoleId());
		loginRes.setFileId(userLogin.getFileId());
		loginRes.setMessage(userLogin.getMessage());
		loginRes.setToken(jwtService.generateJwt(claims));

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}
}
