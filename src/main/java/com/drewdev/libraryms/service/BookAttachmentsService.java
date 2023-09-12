package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.dao.BookAttachmentsDao;
import com.drewdev.libraryms.dao.BooksDao;
import com.drewdev.libraryms.dao.FileDao;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.book_attachments.BookAttachmentResDto;
import com.drewdev.libraryms.dto.book_attachments.BookAttachmentsInsertReqDto;
import com.drewdev.libraryms.model.BookAttachments;
import com.drewdev.libraryms.model.Books;
import com.drewdev.libraryms.model.File;

@Service
public class BookAttachmentsService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private BookAttachmentsDao bookAttachmentsDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private BooksDao booksDao;
	
	public List<BookAttachmentResDto> getByBookId(String id) {
		final List<BookAttachments> attachments = bookAttachmentsDao.getByBookId(id);
		final List<BookAttachmentResDto> attachmentsRes = new ArrayList<>();
		
		for(int i=0; i<attachments.size(); i++) {
			final BookAttachmentResDto attachment = new BookAttachmentResDto();
			final File file = fileDao.getById(File.class, attachments.get(i).getFileId());
			attachment.setFileId(file.getId());
			attachmentsRes.add(attachment);
		}
		
		return attachmentsRes;
	}
	
	public InsertResDto insert(BookAttachmentsInsertReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			final Books book = booksDao.getById(Books.class, data.getBookId());
			
			for(int i=0; i<data.getFiles().size(); i++) {
				final BookAttachments attachments = new BookAttachments();
				attachments.setBookId(book);
				
				final File file = new File();
				file.setFileName(data.getFiles().get(i).getFileName());
				file.setFileExtension(data.getFiles().get(i).getFileExtension());
				fileDao.save(file);
				attachments.setFileId(file);
				bookAttachmentsDao.save(attachments);
			}
			
			response.setId(book.getId());
			response.setMessage("Book Attachment has been Added");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert Book's Attachments Failed!");
		}
		
		return response;
	}
}
