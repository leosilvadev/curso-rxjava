package com.github.leosilvadev.store.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.leosilvadev.store.exceptions.StoreNotFoundException;
import com.github.leosilvadev.store.exceptions.StoreServiceUnavailableException;

import rx.Observable;

@Service
public class StoreService {

	private static final String URL = "http://localhost:9001/v1/stores/%s";

	@Autowired
	RestTemplate restTemplate;

	public Observable<?> findOne(String id) {
		return Observable.defer(() -> {
			try {
				ResponseEntity<?> response = restTemplate.getForEntity(String.format(URL, id), Map.class);
				return Observable.just(response.getBody());
			} catch (HttpClientErrorException ex) {
				if (ex.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
					return Observable.error(new StoreNotFoundException());
				}
				return Observable.error(new StoreServiceUnavailableException());
			}
		});
	}
}
