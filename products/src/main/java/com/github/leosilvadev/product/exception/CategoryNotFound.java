package com.github.leosilvadev.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid Category")
public class CategoryNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2349383855957403567L;

	private String name;
	
	public CategoryNotFound() {}
	
	public CategoryNotFound(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
