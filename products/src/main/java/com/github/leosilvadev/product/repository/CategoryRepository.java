package com.github.leosilvadev.product.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.leosilvadev.product.domain.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

	public List<Category> findAllByActive(Boolean active);

	public Category findOneByName(String name);

	public Category findOneByIdAndActive(Long id, Boolean active);

	public Category findOneByNameAndActive(String name, Boolean active);
	
}
