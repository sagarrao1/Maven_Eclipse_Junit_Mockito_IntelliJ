package com.wiredCoffee.parameterised;

public enum ProductsEnum {
	P1(1),
	P2(2),
	P3(3),
	P4(4),
	P5(5);
	
	private final int productId;	

	private ProductsEnum(int productId) {
		this.productId = productId;
	}

	public int getProductId() {
		return productId;
	}
	
	

}
