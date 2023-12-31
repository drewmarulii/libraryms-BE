package com.drewdev.libraryms.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drewdev.libraryms.model.File;
import com.drewdev.libraryms.service.FileService;

@RestController
@RequestMapping("files")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("{id}")
    public ResponseEntity<?> getFileById(@PathVariable("id") String id) {
        final File file = fileService.getById(id);
        final String fileName = "attachment";
        final byte[] fileBytes = Base64.getDecoder().decode(file.getFileName());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + file.getFileExtension())
                .body(fileBytes);
   }	
}
