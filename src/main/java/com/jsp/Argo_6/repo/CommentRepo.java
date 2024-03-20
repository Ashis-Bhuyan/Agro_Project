package com.jsp.Argo_6.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Argo_6.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
