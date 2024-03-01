package com.jsp.Argo_6.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotSendException extends RuntimeException {
	
	private String msg;

}
