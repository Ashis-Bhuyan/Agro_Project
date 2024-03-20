package com.jsp.Argo_6.util;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String message;
	private int status;
	private T data;
	

}
