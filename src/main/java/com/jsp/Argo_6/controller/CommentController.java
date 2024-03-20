package com.jsp.Argo_6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Argo_6.entity.Comment;
import com.jsp.Argo_6.service.CommentService;
import com.jsp.Argo_6.service.PostService;
import com.jsp.Argo_6.util.ResponseStructure;

@RestController
public class CommentController {
	
	@Autowired
	private PostService postservice;
	@Autowired
	private CommentService service;
	
	// User save Comment
	  @PostMapping("/userComment")
	  public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestParam int pid,@RequestParam int uid,@RequestParam String msgcomment) {
	    return service.saveComment(pid,uid,msgcomment);
	  }
	  
	  //delete
	  @DeleteMapping("/deletecomment")
	  public ResponseEntity<ResponseStructure<Comment>> deleteComment(@RequestParam int commentId){
	    return service.deleteComment(commentId);
	  }

}
