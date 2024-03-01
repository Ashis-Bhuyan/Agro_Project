package com.jsp.Argo_6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.service.UserService;
import com.jsp.Argo_6.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	private UserService userservice;

	@PostMapping("/saveuser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {

		return userservice.saveUser(user);

	}

	@GetMapping("/fetchuser")
	public ResponseEntity<ResponseStructure<User>> fetchUserById(@RequestParam int id) {
		return userservice.fetchUserById(id);

	}
	
	@GetMapping("/fetchall")
	  public List<User> getAllUsers() {
	        return userservice.getAllUsers();
	    }

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<User>> deleteUserById(@RequestParam int id) {
		return userservice.deleteUserById(id);

	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return userservice.updateUser(user);
	}
	
	@GetMapping("/sendotp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email){
		return userservice.sendOtp(email);
	
	}

}
