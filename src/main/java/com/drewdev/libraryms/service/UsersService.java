package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.dao.FileDao;
import com.drewdev.libraryms.dao.RolesDao;
import com.drewdev.libraryms.dao.UsersDao;
import com.drewdev.libraryms.dto.DeleteResDto;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.login.LoginReqDto;
import com.drewdev.libraryms.dto.login.LoginResDto;
import com.drewdev.libraryms.dto.users.UserInsertReqDto;
import com.drewdev.libraryms.dto.users.UserResDto;
import com.drewdev.libraryms.dto.users.UserUpdateReqDto;
import com.drewdev.libraryms.model.File;
import com.drewdev.libraryms.model.Roles;
import com.drewdev.libraryms.model.Users;

@Service
public class UsersService implements UserDetailsService  {
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private RolesDao rolesDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	
	public UserResDto getById(String id) {
		final Users user = usersDao.getById(Users.class, id);
		final UserResDto userRes = new UserResDto();
		
		userRes.setId(user.getId());
		userRes.setUserFullname(user.getUserFullname());
		userRes.setUserEmail(user.getUserEmail());
		userRes.setRoleId(user.getRole().getId());
		userRes.setFileId(user.getFile().getId());
		
		return userRes;
	}
	
	public List<UserResDto> getAll() {
		final List<Users> users = usersDao.getAll(Users.class);
		final List<UserResDto> usersRes = new ArrayList<>();
		
		for(int i=0; i<users.size(); i++) {
			final UserResDto user = new UserResDto();
			user.setId(users.get(i).getId());
			user.setUserFullname(users.get(i).getUserFullname());
			user.setUserEmail(users.get(i).getUserEmail());
			user.setRoleId(users.get(i).getRole().getId());
			user.setFileId(users.get(i).getFile().getId());
			usersRes.add(user);
		}
		
		return usersRes;
	}
	
	public InsertResDto insert(UserInsertReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			final Users user = new Users();
			user.setUserFullname(data.getUserFullname());
			user.setUserEmail(data.getUserEmail());
			final String passwordEncoded = passwordEncoder.encode(data.getUserPassword());
			user.setUserPassword(passwordEncoded);
			
			final Roles role = rolesDao.getById(Roles.class, data.getRoleId());
			user.setRole(role);
			
			final File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			fileDao.save(file);
			user.setFile(file);
			
			usersDao.save(user);
			
			response.setId(user.getId());
			response.setMessage("Insert User Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert User Failed!");
		}
		
		return response;
	}
	
	public UpdateResDto update(UserUpdateReqDto data) {
		final Users user = usersDao.getById(Users.class, data);
		final UpdateResDto response = new UpdateResDto();
		
		try {
			em().getTransaction().begin();
			user.setUserFullname(data.getUserFullname());
			
			final Roles role = rolesDao.getById(Roles.class, data.getId());
			user.setRole(role);
			
			if (data.getFileName() != null) {
				final String fileId = data.getFileId();
				final File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				fileDao.save(file);
				user.setFile(file);
				fileDao.deleteById(File.class, fileId);
			}
			usersDao.saveAndFlush(user);
			
			response.setVersion(user.getVersion());
			response.setMessage("User has been updated");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return response;
	}
	
	public DeleteResDto delete(String id) {
		final DeleteResDto response = new DeleteResDto();
		try {
			em().getTransaction().begin();
			final Users user = usersDao.getById(Users.class, id);
			final String fileId = user.getFile().getId();
			usersDao.deleteById(Users.class, user.getId());
			fileDao.deleteById(File.class, fileId);
			response.setMessage("Delete User Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return response;
	}

}
