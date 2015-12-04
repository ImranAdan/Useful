package org.adani.testing.mocking.models;

public class Order {

	private final String orderId;
	private final int quantity;
	private final Product product;
	
	private OrderState state;
	
	public Order(String orderId, int quantity, Product product) {
		this.orderId = orderId;
		this.quantity = quantity;
		this.product = product;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public Product getProduct() {
		return product;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}
	
	
}
