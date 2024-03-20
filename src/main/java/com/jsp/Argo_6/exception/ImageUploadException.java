package com.jsp.Argo_6.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ImageUploadException extends RuntimeException{
	private String msg;

}
