package com.github.leosilvadev.store.controller;

import static rx.Observable.error;
import static rx.Observable.just;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.leosilvadev.store.domain.Store;
import com.github.leosilvadev.store.exceptions.StoreNotFound;
import com.github.leosilvadev.store.services.StoreService;

import rx.Observable;

@RestController
@RequestMapping("/v1/stores")
public class StoreController {

	@Autowired
	StoreService storeService;
	
	@GetMapping("/{id}")
	public Observable<Store> findOne(@PathVariable String id) {
		return storeService.findOne(id)
			.flatMap(store -> store==null ? error(new StoreNotFound()) : just(store));
	}
	
}
