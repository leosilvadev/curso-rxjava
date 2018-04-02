package com.github.leosilvadev.user.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.leosilvadev.user.domains.User;
import com.github.leosilvadev.user.services.UserService;

import rx.Observable;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/{encodedEmail}")
	public Observable<User> findOne(@RequestHeader(name = "X-Store", required = true) String storeId,
			@PathVariable String encodedEmail) {

		String email = new String(Base64.getDecoder().decode(encodedEmail));
		return userService.findOne(storeId, email);
	}

}
