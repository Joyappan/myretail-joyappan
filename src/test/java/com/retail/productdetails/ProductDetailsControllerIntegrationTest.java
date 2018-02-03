package com.retail.productdetails;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.productdetails.view.ProductDetailsRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductDetailsControllerIntegrationTest {
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void getProductDetailsForSuccess() {
        ResponseEntity<ProductDetailsRequest> responseEntity =  restTemplate.getForEntity("/v1/products/13860428", ProductDetailsRequest.class);
        ProductDetailsRequest  productDetailsRequest = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(13860428, productDetailsRequest.getProductId());
    }
    
    @Test
    public void getProductDetailsForBadRequest() {
        ResponseEntity<ProductDetailsRequest> responseEntity =  restTemplate.getForEntity("/v3/products/1386042", ProductDetailsRequest.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    
    
    @Test
    public void getProductDetailsForInvalid() {
        ResponseEntity<ProductDetailsRequest> responseEntity =  restTemplate.getForEntity("/v1/products/1386042w", ProductDetailsRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
