package com.retail.productdetails.util;

import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Custom rest template class that can replace use of direct rest template for external API calls.
 * 
 * @author Joyappan
 *
 */
@Component
@Scope("prototype")
public class ProductDetailsRestTemplate extends RestTemplate {

	

	public ProductDetailsRestTemplate() {
		 super(new HttpComponentsClientHttpRequestFactory());
	}

	/**
	 * This method gets the connection timeout values for the external system
	 * and sets it to the HttpClientParams.
	 */
	public void setConnectionTimeout(String connectionTimeOut) {

		((HttpComponentsClientHttpRequestFactory) getRequestFactory())
				.setConnectTimeout(Integer.parseInt(connectionTimeOut));
		((HttpComponentsClientHttpRequestFactory) getRequestFactory())
				.setReadTimeout(Integer.parseInt(connectionTimeOut));

	}
	/**
	 * Allowing clients to override  Connection time out and Read time out with different values.
	 * @param connTimeout
	 * @param socketTimeout
	 */
	public void setConnectionTimeout(String connTimeout, String socketTimeout) {

		// Get the connection timeout values for the external system passed

		int httpConnectionTimeOut = Integer.parseInt(connTimeout);
		int httpSocketTimeOut = Integer.parseInt(socketTimeout);

		((HttpComponentsClientHttpRequestFactory) getRequestFactory()).setConnectTimeout(httpConnectionTimeOut);
		((HttpComponentsClientHttpRequestFactory) getRequestFactory()).setReadTimeout(httpSocketTimeOut);

	}

}
