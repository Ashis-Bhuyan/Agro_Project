package com.jsp.Argo_6.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostNotExist extends RuntimeException{
	private String msg;
}
