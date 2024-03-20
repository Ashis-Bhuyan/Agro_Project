package com.jsp.Argo_6.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Argo_6.entity.Image;

public interface ImagesRepo extends JpaRepository<Image, Integer> {
	

}
