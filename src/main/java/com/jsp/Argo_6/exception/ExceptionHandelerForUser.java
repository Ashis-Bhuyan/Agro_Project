package com.jsp.Argo_6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.Argo_6.util.ResponseStructure;

@RestControllerAdvice

public class ExceptionHandelerForUser {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFoundException ex) {
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Sorry! (User Not Found");
		m.setMessage(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);

	}
	
	

}
