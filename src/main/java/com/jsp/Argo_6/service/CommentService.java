package com.jsp.Argo_6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Argo_6.dao.CommentDao;
import com.jsp.Argo_6.dao.PostDao;
import com.jsp.Argo_6.dao.UserDao;
import com.jsp.Argo_6.entity.Comment;
import com.jsp.Argo_6.entity.Post;
import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.exception.PostNotExist;
import com.jsp.Argo_6.exception.UserNotFoundException;
import com.jsp.Argo_6.repo.PostRepo;
import com.jsp.Argo_6.util.ResponseStructure;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentdao;
	@Autowired
	private PostDao postdao;
	@Autowired
	private UserDao userdao;
	@Autowired
	private PostRepo postrepo;

	 //saveComment
	  public ResponseEntity<ResponseStructure<Comment>> saveComment(int pid, int uid, String msgcomment) {
	     Optional<Post> postdata = postrepo.findById(pid);
	    if(postdata!=null) {
	      User userdata = userdao.fetchUserById(uid);
	      if(userdata!=null) {
	        Comment cm=new Comment();
	        cm.setCmnt(msgcomment);
	        cm.setUser(userdata);
	        Comment commentdata = commentdao.saveComment(cm);
	        Post p = postdata.get();
	        List<Comment> listcomment=new ArrayList<Comment>();
	        listcomment.add(commentdata);
	        listcomment.addAll(p.getComment());
	        p.setComment(listcomment);
	        postdao.updatePost(p);
	        ResponseStructure<Comment> r=new ResponseStructure<Comment>();
	        r.setData(commentdata);
	        r.setMessage(msgcomment);
	        r.setStatus(HttpStatus.CREATED.value());
	        
	        return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CREATED);
	      }
	      else {
	        throw new UserNotFoundException(uid+"is not present");
	      }
	    }
	    else {
	      throw new PostNotExist(pid+"is not present");
	    }

	  }

	  public ResponseEntity<ResponseStructure<Comment>> deleteComment(int commentId) {
	    Comment db =commentdao.deleteComment(commentId);
	    if(db!=null) {
	      ResponseStructure<Comment> r= new ResponseStructure<Comment>();
	      r.setData(db);
	      r.setMessage("deleted successfully");
	      r.setStatus(HttpStatus.GONE.value());
	      return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.GONE);
	    }
	    else {
	      throw new UserNotFoundException(commentId+"is not present");
	    }
	  }
}
