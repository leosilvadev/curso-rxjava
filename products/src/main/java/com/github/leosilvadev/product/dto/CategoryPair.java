package com.github.leosilvadev.product.dto;

public class CategoryPair {

	private final Long id;
	private final String name;
	
	public CategoryPair(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
