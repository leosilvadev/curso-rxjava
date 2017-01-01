package com.github.leosilvadev.product.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.leosilvadev.product.domain.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	
	public List<Product> findAllByActive(Boolean active);

}
