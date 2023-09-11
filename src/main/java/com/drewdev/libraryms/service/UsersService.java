package com.drewdev.libraryms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.dao.UsersDao;
import com.drewdev.libraryms.dto.login.LoginReqDto;
import com.drewdev.libraryms.dto.login.LoginResDto;
import com.drewdev.libraryms.model.Users;

@Service
public class UsersService implements UserDetailsService  {
	
	@Autowired
	private UsersDao usersDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final Users user = usersDao.getByEmail(email);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(email, user.getUserPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException(email);
	}
	
	public LoginResDto login(LoginReqDto loginData) {
		final Users user = usersDao.getByEmail(loginData.getUserEmail());
		final LoginResDto loginRes = new LoginResDto();

		if (!user.getIsActive()) {
			loginRes.setMessage("Akun anda nonaktif");
			return loginRes;
		} else {
			loginRes.setId(user.getId());
			loginRes.setFullname(user.getUserFullname());
			loginRes.setRoleId(user.getRole().getId());
			loginRes.setFileId(user.getFile().getId());
			loginRes.setMessage("Akun anda Aktif");
		}

		return loginRes;
	}

}
