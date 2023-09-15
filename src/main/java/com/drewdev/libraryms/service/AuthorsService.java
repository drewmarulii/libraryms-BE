package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.dao.AuthorsDao;
import com.drewdev.libraryms.dto.DeleteResDto;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.authors.AuthorInsertReqDto;
import com.drewdev.libraryms.dto.authors.AuthorResDto;
import com.drewdev.libraryms.dto.authors.AuthorUpdateReqDto;
import com.drewdev.libraryms.model.Authors;
import com.drewdev.libraryms.util.GenerateCode;

@Service
public class AuthorsService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private AuthorsDao authorsDao;
	
	public List<AuthorResDto> getAll() {
		final List<Authors> authors = authorsDao.getAll(Authors.class);
		final List<AuthorResDto> authorsRes = new ArrayList<>();
		
		for(int i=0; i<authors.size(); i++) {
			final AuthorResDto author = new AuthorResDto();
			author.setId(authors.get(i).getId());
			author.setAuthorCode(authors.get(i).getAuthorCode());
			author.setAuthorName(authors.get(i).getAuthorName());
			author.setIsActive(authors.get(i).getIsActive().toString());
			authorsRes.add(author);
		}
		
		return authorsRes;
	}
	
	public AuthorResDto getById(String id) {
		final Authors author = authorsDao.getById(Authors.class, id);
		final AuthorResDto authorRes = new AuthorResDto();
		
		authorRes.setId(author.getId());
		authorRes.setAuthorCode(author.getAuthorCode());
		authorRes.setAuthorName(author.getAuthorName());
		authorRes.setIsActive(Boolean.valueOf(author.getIsActive()).toString());
		
		return authorRes;
	}
	
	public InsertResDto insert(AuthorInsertReqDto data) {
		final InsertResDto response = new InsertResDto();
		try {
			em().getTransaction().begin();
			final Authors author = new Authors();
			author.setAuthorCode(GenerateCode.generateCode());
			author.setAuthorName(data.getAuthorName());
			
			authorsDao.save(author);

			response.setId(author.getId());
			response.setMessage("Insert Author Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert Author Failed!");
		}
		
		return response;
	}
	
	public UpdateResDto update(AuthorUpdateReqDto data) {
		final Authors author = authorsDao.getById(Authors.class, data.getId());
		final UpdateResDto response = new UpdateResDto();
		try {
			em().getTransaction().begin();
			author.setAuthorName(data.getAuthorName());
			authorsDao.saveAndFlush(author);
			
			response.setVersion(author.getVersion());
			response.setMessage("Author has been updated");
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
			final Authors author = authorsDao.getById(Authors.class, id);
			authorsDao.deleteById(Authors.class, author.getId());
			response.setMessage("Delete Author Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return response;
	}
	
}
