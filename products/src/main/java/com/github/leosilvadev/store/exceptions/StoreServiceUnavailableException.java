package com.github.leosilvadev.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Not possible to validate the Store")
public class StoreServiceUnavailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7135413961570888670L;

}
