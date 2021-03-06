package com.github.leosilvadev.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.leosilvadev.product.contracts.ProductRegistration;
import com.github.leosilvadev.product.domain.Product;
import com.github.leosilvadev.product.exception.ProductNotFound;
import com.github.leosilvadev.product.services.ProductService;

import rx.Observable;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public Observable<ResponseEntity<List<Product>>> findAll() {
		return productService.findAllActives().map(ResponseEntity::ok);
	}

	@GetMapping("/{id}")
	public Observable<ResponseEntity<Product>> findOne(@PathVariable Long id) {
		return productService.findOne(id).switchIfEmpty(Observable.error(new ProductNotFound()))
				.map(ResponseEntity::ok);
	}

	@PostMapping()
	public Observable<Product> create(@RequestHeader(name = "X-Store", required = true) String storeId,
			@Valid @RequestBody ProductRegistration productRegistration) {
		return productService.create(storeId, productRegistration).subscribeOn(Schedulers.io());
	}

}
