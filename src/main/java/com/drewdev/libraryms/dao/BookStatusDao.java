package com.drewdev.libraryms.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.drewdev.libraryms.base.AbstractJpaDao;
import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.model.BookStatus;

@Repository
public class BookStatusDao extends AbstractJpaDao {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	public BookStatus getByCode(String statusCode) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(" bs ").append("FROM ").append(" BookStatus bs ").append("WHERE ")
				.append(" bs.statusCode = :statusCode");

		final BookStatus bookStatus = this.em().createQuery(sql.toString(), BookStatus.class)
				.setParameter("statusCode", statusCode).getSingleResult();
		return bookStatus;
	}
}
