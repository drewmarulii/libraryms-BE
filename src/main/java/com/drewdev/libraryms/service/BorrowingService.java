package com.drewdev.libraryms.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.dao.BooksDao;
import com.drewdev.libraryms.dao.BorrowingDao;
import com.drewdev.libraryms.dao.MembersDao;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.borrowing.BorrowingInsertReqDto;
import com.drewdev.libraryms.dto.borrowing.BorrowingResDto;
import com.drewdev.libraryms.dto.borrowing.BorrowingUpdateReqDto;
import com.drewdev.libraryms.model.Books;
import com.drewdev.libraryms.model.Borrowing;
import com.drewdev.libraryms.model.Members;

@Service
public class BorrowingService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private BorrowingDao borrowingDao;
	
	@Autowired
	private BooksDao booksDao;
	
	@Autowired
	private MembersDao membersDao;
	
	public List<BorrowingResDto> getAll() {
		final List<Borrowing> borrowings = borrowingDao.getAll(Borrowing.class);
		final List<BorrowingResDto> borrowingsRes = new ArrayList<>();
		
		for(int i=0; i<borrowings.size(); i++) {
			final BorrowingResDto borrowing = new BorrowingResDto();
			borrowing.setId(borrowings.get(i).getId());
			borrowing.setBookId(borrowings.get(i).getBook().getId());
			borrowing.setDateBorrow(borrowings.get(i).getDateBorrow());
			borrowing.setDateReturn(borrowings.get(i).getDateReturn());
			borrowing.setMemberId(borrowings.get(i).getMember().getId());
			
			borrowingsRes.add(borrowing);
		}
		
		return borrowingsRes;
	}
	
	public BorrowingResDto getById(String id) {
		final Borrowing borrowing = borrowingDao.getById(Borrowing.class, id);
		final BorrowingResDto borrowingRes = new BorrowingResDto();
		
		borrowingRes.setId(borrowing.getId());
		borrowingRes.setBookId(borrowing.getBook().getId());
		borrowingRes.setDateBorrow(borrowing.getDateBorrow());
		borrowingRes.setDateReturn(borrowing.getDateReturn());
		borrowingRes.setMemberId(borrowing.getMember().getId());
		
		return borrowingRes;
	}
	
	public InsertResDto insert(BorrowingInsertReqDto data) {
		final InsertResDto response = new InsertResDto();
		final LocalDateTime now = LocalDateTime.now();
		try {
			em().getTransaction().begin();
			final Borrowing borrowing = new Borrowing();
			final Books book = booksDao.getById(Books.class, data.getBookId());
			borrowing.setBook(book);
			borrowing.setDateBorrow(now);
			
			final Members member = membersDao.getById(Members.class, data.getMemberId());
			borrowing.setMember(member);
			
			borrowingDao.save(borrowing);
			
			response.setId(borrowing.getId());
			response.setMessage("Insert Borrowing Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert Borrowing Failed!");
		}
		
		return response;
	}
	
	public UpdateResDto updateReturnBook(BorrowingUpdateReqDto data) {
		final Borrowing borrowing = borrowingDao.getById(Borrowing.class, data.getId());
		final UpdateResDto response = new UpdateResDto();
		final LocalDateTime now = LocalDateTime.now();
		try {
			em().getTransaction().begin();
			borrowing.setDateReturn(now);
			
			borrowingDao.saveAndFlush(borrowing);
			
			response.setVersion(borrowing.getVersion());
			response.setMessage("Book Has Been Returned");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return response;
	}
}
