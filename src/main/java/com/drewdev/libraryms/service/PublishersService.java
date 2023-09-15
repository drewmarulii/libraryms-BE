package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.dao.PublishersDao;
import com.drewdev.libraryms.dto.DeleteResDto;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.publishers.PublisherInsertMultipleReqDto;
import com.drewdev.libraryms.dto.publishers.PublisherInsertReqDto;
import com.drewdev.libraryms.dto.publishers.PublisherResDto;
import com.drewdev.libraryms.dto.publishers.PublisherUpdateReqDto;
import com.drewdev.libraryms.model.Publishers;

@Service
public class PublishersService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private PublishersDao publishersDao;
	
	public List<PublisherResDto> getAll() {
		final List<Publishers> publishers = publishersDao.getAll(Publishers.class);
		final List<PublisherResDto> publishersRes = new ArrayList<>();
		
		for(int i=0; i<publishers.size(); i++) {
			final PublisherResDto publisher = new PublisherResDto();
			publisher.setId(publishers.get(i).getId());
			publisher.setPublName(publishers.get(i).getPublName());
			publisher.setPublAddress(publishers.get(i).getPublAddress());
			publisher.setPublPhone(publishers.get(i).getPublPhone());
			publisher.setPublFax(publishers.get(i).getPublFax());
			publisher.setPublEmail(publishers.get(i).getPublEmail());
			publisher.setIsActive(publishers.get(i).getIsActive().toString());
			publishersRes.add(publisher);
		}
		
		return publishersRes;
	}
	
	public PublisherResDto getById(String id) {
		final Publishers publisher = publishersDao.getById(Publishers.class, id);
		final PublisherResDto publisherRes = new PublisherResDto();
		
		publisherRes.setId(publisher.getId());
		publisherRes.setPublName(publisher.getPublName());
		publisherRes.setPublAddress(publisher.getPublAddress());
		publisherRes.setPublPhone(publisher.getPublPhone());
		publisherRes.setPublFax(publisher.getPublFax());
		publisherRes.setPublEmail(publisher.getPublEmail());
		publisherRes.setIsActive(publisher.getIsActive().toString());
		
		return publisherRes;
	}
	
	public InsertResDto insert(PublisherInsertReqDto data) {
		final InsertResDto response = new InsertResDto();
		try {
			em().getTransaction().begin();
			final Publishers publisher = new Publishers();
			publisher.setPublName(data.getPublName());
			publisher.setPublAddress(data.getPublAddress());
			publisher.setPublPhone(data.getPublPhone());
			publisher.setPublFax(data.getPublFax());
			publisher.setPublEmail(data.getPublEmail());
			
			publishersDao.save(publisher);
			
			response.setId(publisher.getId());
			response.setMessage("Insert Publisher Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert Publisher Failed!");
		}
		
		return response;
	}
	
	public InsertResDto insertMultiple(PublisherInsertMultipleReqDto datas) {
		final InsertResDto response = new InsertResDto();
		try {
			em().getTransaction().begin();
			for(int i=0; i<datas.getPublishers().size(); i++) {
				final Publishers publisher = new Publishers();
				publisher.setPublName(datas.getPublishers().get(i).getPublName());
				publisher.setPublAddress(datas.getPublishers().get(i).getPublAddress());
				publisher.setPublPhone(datas.getPublishers().get(i).getPublPhone());
				publisher.setPublFax(datas.getPublishers().get(i).getPublFax());
				publisher.setPublEmail(datas.getPublishers().get(i).getPublEmail());
				
				publishersDao.save(publisher);
			}
			
			response.setId("INSERT PUBLISHER");
			response.setMessage("Insert Multiple Publisher Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert Multiple Publisher Failed");
		}
		
		return response;
	}
	
	public UpdateResDto update(PublisherUpdateReqDto data) {
		final Publishers publisher = publishersDao.getById(Publishers.class, data.getId());
		final UpdateResDto response = new UpdateResDto();
		try {
			em().getTransaction().begin();
			publisher.setPublName(data.getPublName());
			publisher.setPublAddress(data.getPublAddress());
			publisher.setPublPhone(data.getPublPhone());
			publisher.setPublFax(data.getPublFax());
			publisher.setPublEmail(data.getPublEmail());
			
			publishersDao.saveAndFlush(publisher);
			
			response.setVersion(publisher.getVersion());
			response.setMessage("Publisher has been updated");
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
			final Publishers publisher = publishersDao.getById(Publishers.class, id);
			publishersDao.deleteById(Publishers.class, publisher.getId());
			
			response.setMessage("Delete Publisher Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return response;
	}
}
