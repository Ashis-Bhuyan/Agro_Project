package com.jsp.Argo_6.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Argo_6.entity.Image;
import com.jsp.Argo_6.entity.Post;
import com.jsp.Argo_6.repo.PostRepo;

@Repository
public class PostDao {
	
	@Autowired
	private PostRepo repo;
	
	public Post savePost(Post post) {
		return repo.save(post);
		
	}
	
	public Post fetchPostById(int id) {
		Optional<Post> db = repo.findById(id);
		if (!db.isEmpty()) {
			return db.get();
		} else {
			return null;
		}
	}
	
	//deleteImage
			public Post deletePostById(int id) {
				Optional<Post> db = repo.findById(id);
				if(db.isEmpty()) {
				return null;
				}else {
					repo.deleteById(id);
					return db.get();
				}
			}
			
			public Post updatePost(Post post) {
			       Optional<Post> db = repo.findById(post.getId());
			       Post data = db.get();
			       if(db.isPresent()) {
			         if(post.getLikes()==0) {
			           post.setLikes(data.getLikes());
			         }
			         if(post.getComment()==null) {
			           post.setComment(data.getComment());
			         }
			         if(post.getImage()==null) {
			           post.setImage(data.getImage());
			         }
			         if(post.getDateandtime()==null) {
			           post.setDateandtime(null);
			         }
			         if(post.getCaption()==null) {
			           post.setCaption(data.getCaption());
			         }
			         if(post.getLocation()==null) {
			           post.setLocation(data.getLocation());
			         }
			         return repo.save(post);
			       }
			       else {
			         return null;
			       }
			    }

}
