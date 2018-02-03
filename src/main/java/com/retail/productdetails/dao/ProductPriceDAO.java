package com.retail.productdetails.dao;

import org.springframework.stereotype.Component;

import com.retail.productdetails.data.ProductPrice;

/**
 * interface for DOA layer and service class is referring this interface for the
 * requests.
 * 
 * @author Joyappan
 *
 */
@Component
public interface ProductPriceDAO {

	/**
	 * Method to return ProductPrice based on productId
	 * 
	 * @param productId
	 * @return
	 */
	public ProductPrice getProductPrice(int productId);

	/**
	 * Method to update the price to DB.
	 * 
	 * @param productPrice
	 */
	public void updateProductPrice(ProductPrice productPrice);
}
