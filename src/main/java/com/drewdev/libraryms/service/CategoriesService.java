package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.dao.CategoriesDao;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.categories.CategoriesInsertReqDto;
import com.drewdev.libraryms.dto.categories.CategoriesResDto;
import com.drewdev.libraryms.dto.categories.CategoriesUpdateReqDto;
import com.drewdev.libraryms.model.Categories;
import com.drewdev.libraryms.util.GenerateCode;

@Service
public class CategoriesService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private CategoriesDao categoriesDao;
	
	public List<CategoriesResDto> getAll() {
		final List<Categories> categories = categoriesDao.getAll(Categories.class);
		final List<CategoriesResDto> categoriesRes = new ArrayList<>();
		
		for(int i=0; i<categories.size(); i++) {
			final CategoriesResDto category = new CategoriesResDto();
			category.setId(categories.get(i).getId());
			category.setCategoryCode(categories.get(i).getCategoryCode());
			category.setCategoryName(categories.get(i).getCategoryName());
			category.setIsActive(Boolean.valueOf(categories.get(i).getIsActive()).toString());
			categoriesRes.add(category);
		}
		
		return categoriesRes;
	}
	
	public CategoriesResDto getById(String id) {
		final Categories category = categoriesDao.getById(Categories.class, id);
		final CategoriesResDto categoryRes = new CategoriesResDto();
		
		categoryRes.setId(category.getId());
		categoryRes.setCategoryCode(category.getCategoryCode());
		categoryRes.setCategoryName(category.getCategoryName());
		categoryRes.setIsActive(Boolean.valueOf(category.getIsActive()).toString());
		
		return categoryRes;
	}
	
	public InsertResDto insert(CategoriesInsertReqDto data) {
		final InsertResDto response = new InsertResDto();
		try {
			em().getTransaction().begin();
			final Categories category = new Categories();
			category.setCategoryCode(GenerateCode.generateCode());
			category.setCategoryName(data.getCategoryName());
			
			categoriesDao.save(category);
			
			response.setId(category.getId());
			response.setMessage("Insert Book's Category Success!");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert Book's Category Failed!");
		}
		
		return response;
	}
	
	public UpdateResDto update(CategoriesUpdateReqDto data) {
		final Categories category = categoriesDao.getById(Categories.class, data.getId());
		final UpdateResDto response = new UpdateResDto();
		
		try {
			em().getTransaction().begin();
			category.setCategoryName(data.getCategoryName());
			categoriesDao.saveAndFlush(category);
			
			response.setVersion(category.getVersion());
			response.setMessage("Category has been updated");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return response;
	}
}
