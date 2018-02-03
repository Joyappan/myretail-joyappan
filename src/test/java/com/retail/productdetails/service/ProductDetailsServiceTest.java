package com.retail.productdetails.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.productdetails.config.PropertyReader;
import com.retail.productdetails.dao.ProductPriceDAOImpl;
import com.retail.productdetails.data.ProductDetails;
import com.retail.productdetails.data.ProductPrice;
import com.retail.productdetails.gateway.ProductDetailsGateway;
import com.retail.productdetails.view.ProductDetailsRequest;
import com.retail.productdetails.view.ProductPriceRequest;

@RunWith(SpringRunner.class)
public class ProductDetailsServiceTest {

	@Mock
	ProductDetailsGateway productDetailsServiceGateway;
	@Mock
	ProductPriceDAOImpl productPriceDAOImpl;
	@Mock
	PropertyReader propertyReader;

	@InjectMocks
	ProductDetailsService productDetailsService;

	@Mock
	ProductDetails productDetails;
	
	ProductDetailsRequest productDetailsRequest;
	
	ProductPriceRequest productPriceRequest;
	
	ProductPrice productPrice;

	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		productPrice= new ProductPrice();
		productPrice.setId(1);
		productPrice.setRegularPrice(new BigDecimal("10.24"));
		
		productPriceRequest= new ProductPriceRequest();
		productPriceRequest.setId(1);
		productPriceRequest.setRegularPrice(new BigDecimal("10.24"));
		
		productDetailsRequest = new ProductDetailsRequest();
		productDetailsRequest.setProductPrice(productPriceRequest);
	}

	@After
	public void teardown() throws Exception {
		productDetailsRequest = null;
	}

	@Test
	public void getProductDetailsTest() {
		when(productDetailsServiceGateway.getProductDetails(1)).thenReturn(productDetails);
		when(productPriceDAOImpl.getProductPrice(1)).thenReturn(new ProductPrice());
		assertNotNull(productDetailsService.getProductDetails(1));
	}

	@Test(expected = Exception.class)
	public void getProductDetailsTestForFailure() {
		when(productDetailsServiceGateway.getProductDetails(1)).thenReturn(productDetails);
		when(productPriceDAOImpl.getProductPrice(1)).thenReturn(null);
		assertNotNull(productDetailsService.getProductDetails(1));
	}

	@Test
	public void updateProductPriceForSuccess() {
		doNothing().when(productPriceDAOImpl).updateProductPrice(productPrice);
		productDetailsService.updateProductPrice(productDetailsRequest);
	}
	
	@Test(expected = Exception.class)
	public void updateProductPriceForFailureNullException() {
		doNothing().when(productPriceDAOImpl).updateProductPrice(productPrice);
		productDetailsService.updateProductPrice(null);
	}
}
