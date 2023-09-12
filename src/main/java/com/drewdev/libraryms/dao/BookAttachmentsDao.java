package com.drewdev.libraryms.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.drewdev.libraryms.base.AbstractJpaDao;
import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.model.BookAttachments;

@Repository
public class BookAttachmentsDao extends AbstractJpaDao {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<BookAttachments> getByBookId(String id) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
			.append(" file_id ")
			.append("FROM ")
			.append(" m_book_attachments mba ")
			.append("WHERE ")
			.append(" book_id = :bookId");
		
		final List<BookAttachments> attahcments = em().createNativeQuery(sql.toString())
				.setParameter("bookId", id)
				.getResultList();
		
		return attahcments;
	}
}
