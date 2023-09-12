package com.drewdev.libraryms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.dao.FileDao;
import com.drewdev.libraryms.model.File;

@Service
public class FileService {
	
	@Autowired
	private FileDao fileDao;
	
	public File getById(String id) {
		final File file = fileDao.getById(File.class, id);
		return file;
	}
}
