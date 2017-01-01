package com.github.leosilvadev.product.contracts;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductRegistration {

	@NotNull
	@NotEmpty
	private String name;

	private String description;

	@NotNull
	private BigDecimal price;

	@NotNull
	private CategoryRegistration category;

	public static class CategoryRegistration {

		private Long id;
		private String name;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public CategoryRegistration getCategory() {
		return category;
	}

	public void setCategory(CategoryRegistration category) {
		this.category = category;
	}

}
