package com.drewdev.libraryms.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService {

	public String getAuthPrincipal() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || auth.getPrincipal() == null)
			throw new RuntimeException("Invalid Login");

		return auth.getPrincipal().toString();
	}
}
