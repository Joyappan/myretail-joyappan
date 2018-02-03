package com.retail.productdetails.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:productconfiguration-test.properties")
public class JsonParserUtilTest {

	JsonParserUtil jsonParserUtil= new JsonParserUtil();
	
	@Value("${productdetailsresponse}")
	private String productdetailsresponseString;
	
	@Test
	public void jsonParsertTestSuccess(){
		assertNotNull(jsonParserUtil.parseJsonResponse(productdetailsresponseString));
	}
	
}

