package com.retail.productdetails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.productdetails.dao.ProductPriceDAO;
import com.retail.productdetails.data.ProductDetails;
import com.retail.productdetails.data.ProductPrice;
import com.retail.productdetails.gateway.ProductDetailsGateway;
import com.retail.productdetails.view.ProductDetailsRequest;
import com.retail.productdetails.view.ProductPriceRequest;
/**
 * Service class to include business logic to call providers based on requirements\.
 * 
 * @author Joyappan
 *
 */
@Service
public class ProductDetailsService {
	
	
	@Autowired
	ProductDetailsGateway productDetailsServiceGateway;
	
	@Autowired
	ProductPriceDAO productPriceDAO;
	
	/**
	 * Service class method to invoke provides to gather product information.
	 * This method calls product gateway to get product information 
	 * and database layer to get product pricing information.
	 * 
	 * @param productId
	 * @return
	 */
	public ProductDetailsRequest getProductDetails(int productId) {
		ProductDetails productDetails = productDetailsServiceGateway.getProductDetails(productId);
		ProductPrice ProductPrice=productPriceDAO.getProductPrice(productId);
		return mapDataObjectToResponse(productDetails,ProductPrice);
		
	}
	
	/**
	 * service calls method to update pricing information in application DB.
	 * @param productDetails
	 */
	public void updateProductPrice(ProductDetailsRequest productDetailsRequest){
		ProductPrice productPrice = new ProductPrice();
		productPrice=mapRequestToDataObject(productDetailsRequest);
		productPriceDAO.updateProductPrice(productPrice);
		
	}
	

	/**
	 * Method maps the request to data object
	 * 
	 * @param productDetailsRequest
	 * @return
	 */
	private ProductPrice mapRequestToDataObject(ProductDetailsRequest productDetailsRequest) {
		ProductPriceRequest priceRequest = productDetailsRequest.getProductPrice();
		ProductPrice productPrice = new ProductPrice();
		productPrice.setId(productDetailsRequest.getProductId());
		productPrice.setRegularPrice(priceRequest.getRegularPrice());
		productPrice.setClearancePrice(priceRequest.getClearancePrice());
		productPrice.setCurrencyType(priceRequest.getCurrencyType());
		productPrice.setPriceType(priceRequest.getPriceType());

		return productPrice;
	}
	
	/**
	 * MEthod to do data layer to controller mapping,.
	 * @param productDetails
	 * @param ProductPrice
	 * @return
	 */
	private ProductDetailsRequest mapDataObjectToResponse(ProductDetails productDetails,ProductPrice ProductPrice){
		
		ProductDetailsRequest detailsRequest = new ProductDetailsRequest();
		detailsRequest.setProductId(productDetails.getProductId());
		detailsRequest.setProductDisplayName(productDetails.getProductDisplayName());
		detailsRequest.setUpc(productDetails.getUpc());
		ProductPriceRequest priceRequest = new ProductPriceRequest();
		priceRequest.setId(ProductPrice.getId());
		priceRequest.setRegularPrice(ProductPrice.getRegularPrice());
		priceRequest.setClearancePrice(ProductPrice.getClearancePrice());
		priceRequest.setCurrencyType(ProductPrice.getCurrencyType());
		priceRequest.setPriceType(ProductPrice.getPriceType());
		detailsRequest.setProductPrice(priceRequest);
		return detailsRequest;
	}
}
