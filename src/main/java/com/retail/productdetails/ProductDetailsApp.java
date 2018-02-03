package com.retail.productdetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.retail.productdetails.util.DbConnectionUtil;

/**
 * The application startup class using the spring boot application.
 * @author Joyappan
 *
 */
@SpringBootApplication
public class ProductDetailsApp {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsApp.class);
	
	public static void main(String args[]){
		LOGGER.info("********** Starting the application Product Details Service **********");
		SpringApplication.run(ProductDetailsApp.class, args);
	}

}
