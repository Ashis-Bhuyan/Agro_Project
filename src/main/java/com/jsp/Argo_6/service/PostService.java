package com.jsp.Argo_6.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Argo_6.dao.PostDao;
import com.jsp.Argo_6.dao.UserDao;
import com.jsp.Argo_6.entity.Image;
import com.jsp.Argo_6.entity.Post;
import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.exception.EmailNotSendException;
import com.jsp.Argo_6.exception.ImageUploadException;
import com.jsp.Argo_6.exception.UserNotFoundException;
import com.jsp.Argo_6.util.ResponseStructure;

@Service
public class PostService {
	@Autowired
	private PostDao dao;

	@Autowired
	private UserDao userdao;

	@Autowired
	private ImageService imageservice;

	// saving userPost
	public ResponseEntity<ResponseStructure<Post>> savePost(int id, MultipartFile file, String date, String caption,
			String location) throws IOException {
		User userdata = userdao.fetchUserById(id);
		if (userdata != null) {
			Image image = new Image();
			image.setName(file.getOriginalFilename());
			image.setData(file.getBytes());
			Post post = new Post();
			post.setImage(image);
			post.setCaption(caption);
			post.setLocation(location);
			post.setDateandtime(date);
			Post postdata = dao.savePost(post);
			ResponseStructure<Post> m = new ResponseStructure<Post>();
			if (postdata != null) {
				ArrayList<Post> postlist = new ArrayList<Post>();
				postlist.add(postdata);
				postlist.addAll(userdata.getPosts());
				userdata.setPosts(postlist);
				userdao.updateUser(userdata);
				m.setData(postdata);
				m.setMessage("post uploaded successfully......");
				m.setStatus(HttpStatus.OK.value());
			}
			return new ResponseEntity<ResponseStructure<Post>>(m, HttpStatus.OK);
		}
		throw new UserNotFoundException(id + " : is not present");
	}

//		fetching the post

	public ResponseEntity<ResponseStructure<Post>> fetchPostById(int id) {
		Post db = dao.fetchPostById(id);
		if (db != null) {

			ResponseStructure<Post> m = new ResponseStructure<Post>();
			m.setData(db);
			m.setMessage("User found successfully......!");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Post>>(m, HttpStatus.FOUND);
		} else {
			throw new UserNotFoundException("post not found for this id = " + id);
		}

	}

// delete the post
	public ResponseEntity<ResponseStructure<Post>> deletePost(int id) {
		Post post = dao.fetchPostById(id);
		if (post != null) {
			List<User> users = userdao.fetchAll();
			for (User u : users) {
				List<Post> posts = u.getPosts();
				if (posts.contains(post)) {
					posts.remove(post);
					userdao.updateUser(u);
					dao.deletePostById(id);
					break;
				}
			}
			ResponseStructure<Post> s = new ResponseStructure<Post>();
			s.setData(post);
			s.setMessage("post deleted successfully......");
			s.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Post>>(s, HttpStatus.OK);
		} else {
			throw new UserNotFoundException("user not found for your search:" + id);
		}
	}
}
