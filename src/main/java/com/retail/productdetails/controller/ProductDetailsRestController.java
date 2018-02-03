package com.retail.productdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.productdetails.data.ProductDetails;
import com.retail.productdetails.exception.ApplicationErrorCode;
import com.retail.productdetails.exception.InputValidationException;
import com.retail.productdetails.service.ProductDetailsService;
import com.retail.productdetails.view.ProductDetailsRequest;

/**
 * This is controller class for the application. The class receives the requests
 * and based on Request mapping, will be routed to methods.
 * 
 * @author Joyappan
 *
 */
@RestController
public class ProductDetailsRestController {

	@Autowired
	ProductDetailsService productDetailsService;

	/**
	 * REST service to retrieve product information based on product ID.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/v1/products/{id}", method = RequestMethod.GET)
	public ProductDetailsRequest getProductDetails(@PathVariable String id) {
		int productId;
		try {
			productId = Integer.parseInt(id);
		} catch (Exception e) {
			throw new InputValidationException(ApplicationErrorCode.INPUT_VALIDATION_ERROR,
					" Input validation exception and the given id is not an integer " + id + ".");
		}
		return productDetailsService.getProductDetails(productId);
	}

	/**
	 * REST method supports the product rice update based on a given input.
	 * 
	 * @param id
	 * @param productDetails
	 * @return
	 */
	@RequestMapping(value = "/v1/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProductDetails> getProductDetails(@PathVariable String id,
			@Validated @RequestBody ProductDetailsRequest productDetailsRequest) {
		productDetailsService.updateProductPrice(productDetailsRequest);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
