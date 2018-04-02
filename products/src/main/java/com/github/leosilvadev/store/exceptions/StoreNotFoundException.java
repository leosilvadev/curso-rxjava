package com.github.leosilvadev.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Store")
public class StoreNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7122150547626886895L;

}
