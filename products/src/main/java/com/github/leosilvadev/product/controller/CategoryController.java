package com.github.leosilvadev.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.leosilvadev.product.domain.Category;
import com.github.leosilvadev.product.exception.CategoryNotFound;
import com.github.leosilvadev.product.services.CategoryService;

import rx.Observable;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public Observable<ResponseEntity<List<Category>>> findAll() {
		return categoryService.findAllActives().map(ResponseEntity::ok).onErrorReturn(ex -> {
			return ResponseEntity.ok(new ArrayList<>());
		});
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Observable<Category> create(@Valid @RequestBody Category category) {
		return categoryService.create(category);
	}

	@DeleteMapping("/{id}")
	public Observable<Category> delete(@PathVariable Long id) {
		return categoryService.delete(id);
	}

	@GetMapping("/{id}")
	public Observable<Category> findOne(@PathVariable Long id) {
		return categoryService.findOne(id)
				.switchIfEmpty(Observable.error(new CategoryNotFound()));
	}
}
