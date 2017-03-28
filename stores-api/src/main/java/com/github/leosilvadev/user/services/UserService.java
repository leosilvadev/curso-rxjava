package com.github.leosilvadev.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.leosilvadev.store.services.StoreService;
import com.github.leosilvadev.user.domains.User;
import com.github.leosilvadev.user.exceptions.UserNotFoundException;

import rx.Observable;

@Service
public class UserService {

	@Autowired
	StoreService storeService;
	
	public Observable<User> findOne(String storeId, String email) {
		return storeService.findOne(storeId).flatMap(store -> 
			Observable.from(store.getUsers())
			
		).filter(user -> {
			return user.getEmail().equals(email);
			
		}).single().onErrorResumeNext(Observable.error(new UserNotFoundException()));
	}

}
