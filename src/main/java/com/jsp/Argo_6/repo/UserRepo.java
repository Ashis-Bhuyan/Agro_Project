package com.jsp.Argo_6.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Argo_6.entity.User;


public interface UserRepo extends JpaRepository<User, Integer>{
	@Query("Select a from User a where email=?1")
	public User findByEmail(String email);
	

}
