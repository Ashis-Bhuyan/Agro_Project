package com.jsp.Argo_6.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailEXistException extends RuntimeException{
	
	private String msg;

}
