package com.github.leosilvadev.product.services;

import static rx.Observable.defer;
import static rx.Observable.error;
import static rx.Observable.just;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.github.leosilvadev.product.contracts.ProductRegistration;
import com.github.leosilvadev.product.contracts.ProductRegistration.CategoryRegistration;
import com.github.leosilvadev.product.domain.Product;
import com.github.leosilvadev.product.exception.ProductNotFound;
import com.github.leosilvadev.product.repository.ProductRepository;

import rx.Observable;

@Service
public class ProductService {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductRepository productRepository;

	public Observable<List<Product>> findAllActives() {
		return defer(() -> {
			return just(productRepository.findAllByActive(true));
		});
	}

	public Observable<Product> findOne(Long id) {
		return defer(() -> {
			Product product = productRepository.findOne(id);
			if (product == null)
				return error(new ProductNotFound());

			return just(productRepository.findOne(id));
		});
	}

	public Observable<Product> create(ProductRegistration productRegistration) {
		return just(productRegistration).flatMap(registration -> {
			CategoryRegistration category = registration.getCategory();
			return categoryService.create(category.getId(), category.getName()).map(cat -> {
				return Pair.of(cat, registration);
			});
			
		}).flatMap(pair -> {
			ProductRegistration registration = pair.getSecond();
			Product product = new Product(
					registration.getName(), 
					registration.getDescription(),
					registration.getPrice(), pair.getFirst()
			);
			return create(product);
		});
	}

	private Observable<Product> create(Product product) {
		return defer(() -> {
			return just(productRepository.save(product));
		});
	}

}
