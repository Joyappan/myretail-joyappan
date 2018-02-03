package com.retail.productdetails.gateway;

import java.net.SocketTimeoutException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.retail.productdetails.config.PropertyReader;
import com.retail.productdetails.data.ProductDetails;
import com.retail.productdetails.exception.ApplicationErrorCode;
import com.retail.productdetails.exception.ProductException;
import com.retail.productdetails.util.JsonParserUtil;
import com.retail.productdetails.util.RestTemplateUtil;

/**
 * Gateway class that handling external API calls. 
 * Now this class is taking care of integrated with product details API.
 * 
 * @author Joyappan
 *
 */
@Service
public class ProductDetailsGateway {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsGateway.class);

	@Autowired
	PropertyReader propertyReader;

	@Autowired
	JsonParserUtil JsonParserUtil;
	
	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	/*@Autowired
	ProductDetailsRestTemplate productDetailsRestTemplate;*/
	
	/**
	 * This method takes care of integration of response from external API and POJO conversion of the responses.
	 * This validates the response based on final output and throw exception if necessary..
	 * 
	 * @param productId
	 * @return
	 */
	public ProductDetails getProductDetails(int productId) {
		
		ProductDetails productDetails = null;
		try {
			String serviceUri = propertyReader.getProductServiceuri() + productId+ propertyReader.getProductUrlpattern();
			String gatewayResponse= restTemplateUtil.getGatewayResponse( serviceUri);
			productDetails= JsonParserUtil.parseJsonResponse(gatewayResponse);
			if(productDetails!= null && productDetails.getProductId() != productId){
				LOGGER.error("Product id mismatch in the response.  Product id in request : " +productId  + " ::Product Id in response" + productDetails.getProductId());
				throw new ProductException(ApplicationErrorCode.PRODUCTGATEWAY_INVALIDRESPONSE_ERROR,"Product Id mismatch in the response.");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			if (ExceptionUtils.getRootCause(exception) instanceof SocketTimeoutException) {
				LOGGER.error("Timeout error while calling Product Gateway , "+ exception.getMessage());
				throw new ProductException(ApplicationErrorCode.PRODUCTGATEWAY_TIMEOUT_ERROR, "Product details service call failed.");
			} else if (ExceptionUtils.getRootCause(exception) instanceof ProductException){
				LOGGER.error("Product service exception"+ exception.getMessage());
				throw exception;
			}
			throw new ProductException(ApplicationErrorCode.PRODUCTGATEWAY_SERVICEFAILURE_ERROR, "Product Details service call failed.");
		}
		return productDetails;

	}
	
	/**
	 * Method calls Product API and 
	 * @param productId
	 * @return
	 */
	/*private String getGatewayResponse(int productId){
		String serviceUri = propertyReader.getProductServiceuri() + productId+ propertyReader.getProductUrlpattern();
		productDetailsRestTemplate.setConnectionTimeout(propertyReader.getRestTemplateConnectionTimeout(), propertyReader.getRestTemplateReadTimeout());
		String gatewayResponse = productDetailsRestTemplate.getForObject(serviceUri, String.class);
		
		LOGGER.debug("ProductGatewy response :: "+ gatewayResponse);
		
		return gatewayResponse;
	}*/
	
	/**
	 * This method calls external API to get the product details using rest template.
	 * The time out for the external connection is handled in this class.
	 * @param productId
	 * @return
	 */
	public String getGatewayResponse(int productId){
		
		String serviceUri = propertyReader.getProductServiceuri() + productId+ propertyReader.getProductUrlpattern();
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(Integer.parseInt(propertyReader.getProductdetailsConnectionTimeout()));
		httpComponentsClientHttpRequestFactory.setReadTimeout(Integer.parseInt(propertyReader.getProductdetailsSocketTimeout()));
		RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
		String gatewayResponse = restTemplate.getForObject(serviceUri, String.class);
		
		LOGGER.debug("ProductGatewy response :: "+ gatewayResponse);
		
		return gatewayResponse;
	}
	
}
