package com.retail.productdetails.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.retail.productdetails.config.PropertyReader;
import com.retail.productdetails.gateway.ProductDetailsGateway;

/**
 * This class takes care of Rest template actions for this application.
 * application can pass URL sting along with optional timeouts.
 * 
 * This class can extend to accept header values in the parameter if required.
 * 
 * @author Joyappan
 *
 */
@Component
public class RestTemplateUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtil.class);

	@Autowired
	PropertyReader propertyReader;

	/**
	 * Method accepts URI path  and  get the response corresponding the API calls.
	 * This method uses default application timeouts. 
	 * 
	 * @param externlUri
	 * @return
	 */
	public String getGatewayResponse(String externlUri) {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory
				.setConnectionRequestTimeout(Integer.parseInt(propertyReader.getProductdetailsConnectionTimeout()));
		httpComponentsClientHttpRequestFactory
				.setReadTimeout(Integer.parseInt(propertyReader.getProductdetailsSocketTimeout()));
		RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
		String gatewayResponse = restTemplate.getForObject(externlUri, String.class);

		LOGGER.debug("ProductGatewy response :: " + gatewayResponse);

		return gatewayResponse;
	}

	
	/**
	 * Method accepts URI path  and  get the response corresponding the API calls.
	 * This method have option to accept timeout values from clients and use that for API calls.
	 * 
	 * @param enternlUri
	 * @param connectiontimeout
	 * @param socketTimeout
	 * @return
	 */
	public String getGatewayResponse(String externlUri, int connectiontimeout, int socketTimeout) {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory
				.setConnectionRequestTimeout(connectiontimeout);
		httpComponentsClientHttpRequestFactory
				.setReadTimeout(socketTimeout);
		RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
		String gatewayResponse = restTemplate.getForObject(externlUri, String.class);

		LOGGER.debug("ProductGatewy response :: " + gatewayResponse);

		return gatewayResponse;
	}
	
}
