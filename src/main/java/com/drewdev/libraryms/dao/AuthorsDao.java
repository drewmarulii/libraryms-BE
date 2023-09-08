package com.drewdev.libraryms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.drewdev.libraryms.model.Authors;

public class AuthorsDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Authors> getAll() {
		final String sql = "SELECT "
				+ " a "
				+ "FROM "
				+ " Authors a";
		final List<Authors> authors = this.em.createQuery(sql, Authors.class).getResultList();
		return authors;
	}
	
	public Authors insert(Authors author) {
		em.persist(author);
		return author;
	}
	
	public Authors getAuthorById(String id) {
		final Authors author = this.em.find(Authors.class, id);
		return author;
	}
}
