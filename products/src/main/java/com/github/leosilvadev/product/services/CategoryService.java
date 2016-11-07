package com.github.leosilvadev.product.services;

import static rx.Observable.defer;
import static rx.Observable.error;
import static rx.Observable.just;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.leosilvadev.product.domain.Category;
import com.github.leosilvadev.product.exception.CategoryNotFound;
import com.github.leosilvadev.product.repository.CategoryRepository;

import rx.Observable;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public Observable<List<Category>> findAllActives() {
		return defer(() -> {
			return just(categoryRepository.findAllByActive(true));
		});
	}

	public Observable<Category> findOne(Long id) {
		return defer(() -> {
			Category category = categoryRepository.findOne(id);
			if (category == null)
				return error(new CategoryNotFound());

			return just(category);
		});
	}

	public Observable<Category> findOneActive(Long id) {
		return defer(() -> {
			Category category = categoryRepository.findOneByIdAndActive(id, true);
			if (category == null)
				return error(new CategoryNotFound());

			return just(category);
		});
	}

	public Observable<Category> findOneActive(String name) {
		return defer(() -> {
			Category category = categoryRepository.findOneByNameAndActive(name, true);
			if (category == null)
				return error(new CategoryNotFound());

			return just(category);
		});
	}

	public Observable<Category> findOne(String name) {
		return defer(() -> {
			Category category = categoryRepository.findOneByName(name);
			if (category == null)
				return error(new CategoryNotFound());

			return just(category);
		});
	}

	public Observable<Category> create(Category category) {
		return defer(() -> {
			return just(categoryRepository.save(category));
		});
	}

	public Observable<Category> create(Long id, String name) {
		Observable<Pair<Category, String>> obsFindById = just(Pair.of(id, name)).flatMap(pair -> {
			if (pair.getLeft() == null)
				return just(Pair.of(null, pair.getRight()));

			return findOneActive(pair.getLeft()).map(category -> {
				return Pair.of(category, pair.getRight());
			});
		});

		return obsFindById.flatMap(pair -> {
			if (pair.getLeft() == null) {
				return error(new CategoryNotFound(pair.getRight()));
			} else {
				return just(pair.getLeft());
			}
		}).onErrorResumeNext(ex -> {
			CategoryNotFound notFound = (CategoryNotFound) ex;

			if (notFound.getName() == null)
				return error(new CategoryNotFound());

			return findOneActive(notFound.getName()).flatMap(category -> {
				return just(category);
			}).onErrorResumeNext(exception -> {
				return create(new Category(notFound.getName()));
			});
		});
	}

	public Observable<Category> delete(Long id) {
		return Observable.defer(() -> {
			Category category = categoryRepository.findOne(id);
			category.inactive();
			return Observable.just(categoryRepository.save(category));
		});
	}

}
