package com.jsp.Argo_6.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Argo_6.dao.PostDao;
import com.jsp.Argo_6.entity.Post;
import com.jsp.Argo_6.service.PostService;
import com.jsp.Argo_6.util.ResponseStructure;

@RestController
public class PostController {
	@Autowired
	private PostService service;

	@Autowired
	private PostDao dao;

	@PostMapping("/savepost")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam int id, @RequestParam("file") MultipartFile file,
			@RequestParam String date, @RequestParam String caption, @RequestParam String location) throws IOException {
		return service.savePost(id, file, date, caption, location);

	}

	@GetMapping("/fetchpost")
	public ResponseEntity<ResponseStructure<Post>> fetchPostById(@RequestParam int id) {
		return service.fetchPostById(id);

	}
	
	@DeleteMapping("/deletepost")
	public ResponseEntity<ResponseStructure<Post>> deleteImage(@RequestParam int id){
		return service.deletePost(id);
	}

	

}
