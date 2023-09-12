package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.dao.AuthorsDao;
import com.drewdev.libraryms.dao.BookStatusDao;
import com.drewdev.libraryms.dao.BooksDao;
import com.drewdev.libraryms.dao.CategoriesDao;
import com.drewdev.libraryms.dao.FileDao;
import com.drewdev.libraryms.dao.PublishersDao;
import com.drewdev.libraryms.dto.DeleteResDto;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.books.BookUpdateReqDto;
import com.drewdev.libraryms.dto.books.BooksInsertReqDto;
import com.drewdev.libraryms.dto.books.BooksResDto;
import com.drewdev.libraryms.model.Authors;
import com.drewdev.libraryms.model.BookStatus;
import com.drewdev.libraryms.model.Books;
import com.drewdev.libraryms.model.Categories;
import com.drewdev.libraryms.model.File;
import com.drewdev.libraryms.model.Publishers;

@Service
public class BooksService {
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private BooksDao booksDao;
	
	@Autowired
	private AuthorsDao authorsDao;
	
	@Autowired
	private PublishersDao publishersDao;
	
	@Autowired
	private CategoriesDao categoriesDao;
	
	@Autowired
	private BookStatusDao bookStatusDao;
	
	@Autowired
	private FileDao fileDao;
	
	public List<BooksResDto> getAll() {
		final List<Books> books = booksDao.getAll(Books.class);
		final List<BooksResDto> booksRes = new ArrayList<>();
		
		for(int i=0; i<books.size(); i++) {
			final BooksResDto book = new BooksResDto();
			book.setId(books.get(i).getId());
			book.setBookIsbn(books.get(i).getBookIsbn());
			book.setBookTitle(books.get(i).getBookTitle());
			book.setBookPublishdate(books.get(i).getBookPublishDate().toString());
			book.setAuthorId(books.get(i).getAuthor().getId());
			book.setPublisherId(books.get(i).getPublisher().getId());
			book.setCategoryId(books.get(i).getCategory().getId());
			book.setStatusId(books.get(i).getStatus().getId());
			book.setFileId(books.get(i).getFile().getId());
			booksRes.add(book);
		}
		
		return booksRes;
	}
	
	public BooksResDto getById(String id) {
		final Books book = booksDao.getById(Books.class, id);
		final BooksResDto bookRes = new BooksResDto();
		
		bookRes.setId(book.getId());
		bookRes.setBookIsbn(book.getBookIsbn());
		bookRes.setBookTitle(book.getBookTitle());
		bookRes.setBookPublishdate(book.getBookPublishDate().toString());
		bookRes.setAuthorId(book.getAuthor().getId());
		bookRes.setPublisherId(book.getPublisher().getId());
		bookRes.setCategoryId(book.getCategory().getId());
		bookRes.setStatusId(book.getStatus().getId());
		bookRes.setFileId(book.getFile().getId());		
		
		return bookRes;
	}
	
	public InsertResDto insert(BooksInsertReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			final Books book = new Books();
			book.setBookIsbn(data.getBookIsbn());
			book.setBookTitle(data.getBookTitle());
			book.setBookPublishDate(data.getBookPublishdate());
			
			final Authors author = authorsDao.getById(Authors.class, data.getAuthorId());
			book.setAuthor(author);
			
			final Publishers publisher = publishersDao.getById(Publishers.class, data.getPublisherId());
			book.setPublisher(publisher);
			
			final Categories category = categoriesDao.getById(Categories.class, data.getCategoryId());
			book.setCategory(category);
			
			final BookStatus status = bookStatusDao.getById(BookStatus.class, data.getStatusId());
			book.setStatus(status);
			
			final File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			fileDao.save(file);
			book.setFile(file);
			
			booksDao.save(book);
			
			response.setId(book.getId());
			response.setMessage("Insert Book Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert Book Failed!");
		}
		
		return response;
	}
	
	public UpdateResDto update(BookUpdateReqDto data) {
		final Books book = booksDao.getById(Books.class, data.getId());
		final UpdateResDto response = new UpdateResDto();
		
		try {
			em().getTransaction().begin();
			book.setBookIsbn(data.getBookIsbn());
			book.setBookTitle(data.getBookTitle());
			book.setBookPublishDate(data.getBookPublishdate());
			
			final Authors author = authorsDao.getById(Authors.class, data.getAuthorId());
			book.setAuthor(author);
			
			final Publishers publisher = publishersDao.getById(Publishers.class, data.getPublisherId());
			book.setPublisher(publisher);
			
			final Categories category = categoriesDao.getById(Categories.class, data.getCategoryId());
			book.setCategory(category);
			
			final BookStatus status = bookStatusDao.getById(BookStatus.class, data.getStatusId());
			book.setStatus(status);
			
			if (data.getFileName() !=null) {
				final String fileId = data.getFileId();
				final File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				fileDao.save(file);
				book.setFile(file);
				fileDao.deleteById(File.class, fileId);
			}
			
			booksDao.saveAndFlush(book);
			
			response.setVersion(book.getVersion());
			response.setMessage("Book has been updated");
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
			final Books book = booksDao.getById(Books.class, id);
			final String fileId = book.getFile().getId();
			booksDao.deleteById(Books.class, book.getId());
			fileDao.deleteById(File.class, fileId);
			response.setMessage("Delete Book Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return response;
	}
}
