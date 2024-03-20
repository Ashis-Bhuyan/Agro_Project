package com.jsp.Argo_6.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException{
	
	private String msg;

}
