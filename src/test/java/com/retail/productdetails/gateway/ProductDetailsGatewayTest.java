package com.retail.productdetails.gateway;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.retail.productdetails.config.PropertyReader;
import com.retail.productdetails.data.ProductDetails;
import com.retail.productdetails.util.JsonParserUtil;
import com.retail.productdetails.util.ProductDetailsRestTemplate;
import com.retail.productdetails.util.RestTemplateUtil;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:productconfiguration-test.properties")
public class ProductDetailsGatewayTest {

	@InjectMocks
	ProductDetailsGateway detailsGateway;
	
	@Mock
	PropertyReader propertyReader;
	
	@Mock
	RestTemplateUtil restTemplateUtil;
	
	@Mock
	JsonParserUtil jsonParserUtil;
	
	ProductDetails productDetails=null;
	
	MockRestServiceServer server;

	ProductDetailsRestTemplate productDetailsRestTemplate;

	String serviceUri;
	
	@Value("${productdetailsresponse}")
	private String productdetailsresponseString;

	@Value("${product.service}")
	private String productServiceuri;

	@Value("${product.urlpattern}")
	private String productUrlpattern;
	
	
	@Value("${resttemplate.productdetailsconnectiontimeout}")
	private String productdetailsConnectionTimeout;

	@Value("${resttemplate.productdetailssockettimeout}")
	private	String productdetailsSocketTimeout;
	
	String uriPathMock="http://redsky.target.com/v1/pdp/tcin/1?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	
	@Before
	public void setUp() throws Exception {
		productDetails = new ProductDetails();
		productDetails.setProductId(1);
		productDetailsRestTemplate = new ProductDetailsRestTemplate();
		server = MockRestServiceServer.bindTo(productDetailsRestTemplate).build();
		serviceUri="http://localhost:8080/v1/products/13860428";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void mockRestTemplateTest() {
		server.expect(requestTo(serviceUri)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess());
		productDetailsRestTemplate.getForObject(serviceUri, String.class);
		server.verify();
	}
	
	@Test
	public void getProductDetailsTest(){
		when(propertyReader.getProductServiceuri()).thenReturn(productServiceuri);
		when(propertyReader.getProductUrlpattern()).thenReturn(productUrlpattern);
		when(propertyReader.getProductdetailsConnectionTimeout()).thenReturn(productdetailsConnectionTimeout);
		when(propertyReader.getProductdetailsSocketTimeout()).thenReturn(productdetailsSocketTimeout);
		when(restTemplateUtil.getGatewayResponse(uriPathMock)).thenReturn("1");
		when(jsonParserUtil.parseJsonResponse("1")).thenReturn(productDetails);
		assertNotNull(detailsGateway.getProductDetails(1));
	}

	@Test
	public void getProductDetailsTestForFailure(){
		when(propertyReader.getProductServiceuri()).thenReturn(productServiceuri+"{");
		when(propertyReader.getProductUrlpattern()).thenReturn(productUrlpattern);
		when(propertyReader.getProductdetailsConnectionTimeout()).thenReturn(productdetailsConnectionTimeout);
		when(propertyReader.getProductdetailsSocketTimeout()).thenReturn(productdetailsSocketTimeout);
		when(restTemplateUtil.getGatewayResponse(uriPathMock)).thenReturn("1");
		when(jsonParserUtil.parseJsonResponse("1")).thenReturn(productDetails);
		assertNull(detailsGateway.getProductDetails(1));
	}
}
