package com.jsp.Argo_6.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Argo_6.dao.ImagesDao;
import com.jsp.Argo_6.entity.Image;
import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.service.ImageService;
import com.jsp.Argo_6.util.ResponseStructure;


@RestController
public class ImageController {


    @Autowired
    private ImageService service;
    @Autowired
    private ImagesDao dao;

  
    @PostMapping("/upload")
    public  Image uploadImage(@RequestParam("file") MultipartFile file, @RequestParam int id) throws IOException{
		return service.imageSave(id, file);	 
 }
 
	//user fetching fetch by id
	@GetMapping("/imageFetching")
	public ResponseEntity<byte[]> fetchById(@RequestParam int id){	
		return service.fetchById(id);
	}
	
	@PutMapping("/imageupdating")
	public ResponseEntity<ResponseStructure<Image>> fetchById(@RequestParam int id ,@RequestParam("file") MultipartFile file) throws IOException{	
		return service.updateById(id,file);
	}
	
	@DeleteMapping("/deleteimage")
	public String deleteimgById(@RequestParam int id) {
		return service.deleteImageById(id);
	}


}
