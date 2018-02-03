package com.retail.productdetails.data;

import org.springframework.stereotype.Component;

/**
 * POJO to hold product details information.
 * @author Joyappan
 *
 */
@Component
public class ProductDetails {

	int productId;
	String upc;
	String productDisplayName;
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductDisplayName() {
		return productDisplayName;
	}
	public void setProductDisplayName(String productDisplayName) {
		this.productDisplayName = productDisplayName;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	
}
