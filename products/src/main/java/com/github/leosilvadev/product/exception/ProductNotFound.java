package com.github.leosilvadev.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid Product")
public class ProductNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2349383855957403567L;

}
