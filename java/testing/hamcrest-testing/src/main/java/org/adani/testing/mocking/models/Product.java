package org.adani.testing.mocking.models;

import java.math.BigDecimal;

public class Product {

	private final String productId;
	private final String description;
	private final BigDecimal price;
	
	public Product(String productId, String description, BigDecimal price) {
		this.productId = productId;
		this.description = description;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	
	
}
