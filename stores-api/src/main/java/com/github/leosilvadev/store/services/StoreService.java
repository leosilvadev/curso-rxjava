package com.github.leosilvadev.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.leosilvadev.store.domain.Store;
import com.github.leosilvadev.store.repository.StoreRepository;

import rx.Observable;
import static rx.Observable.*;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepository;
	
	public Observable<Store> findOne(String id) {
		return defer(() -> just(storeRepository.findOne(id)));
	}
	
}
