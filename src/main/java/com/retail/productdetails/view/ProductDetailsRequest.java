package com.retail.productdetails.view;

import org.springframework.stereotype.Component;

import com.retail.productdetails.validator.MandatoryField;

@Component
@MandatoryField(message = "missing required field")
public class ProductDetailsRequest {

	// @Pattern(regexp = "^$|^[0-9]*$", message = "invalid product Id")
	int productId;
	String upc;
	String productDisplayName;
	ProductPriceRequest productPriceRequest;

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

	public ProductPriceRequest getProductPrice() {
		return productPriceRequest;
	}

	public void setProductPrice(ProductPriceRequest productPriceRequest) {
		this.productPriceRequest = productPriceRequest;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}


	@Override
	public String toString() {

		return "ProductDetailsRequest [productId=" + productId + ", upc=" + upc + ", productDisplayName="
				+ productDisplayName + ", productPriceRequest=" + productPriceRequest + "]";
	}

}
