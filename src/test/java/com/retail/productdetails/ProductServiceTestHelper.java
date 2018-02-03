package com.retail.productdetails;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.retail.productdetails.data.ProductPrice;

@Component
public class ProductServiceTestHelper {
	
	
	public ProductPrice getMokedPrice(){
		ProductPrice productPrice = new ProductPrice();
		productPrice.setId(138604288);
		productPrice.setClearancePrice(new BigDecimal(22.44));
		productPrice.setRegularPrice(new BigDecimal(33.44));
		productPrice.setCurrencyType("USD");
		return productPrice;
	}
	
	
	public List<ProductPrice> getProductPriceList() {
		List<ProductPrice> productPriceList = new ArrayList<ProductPrice>();
		productPriceList.add(getMokedPrice());
		return productPriceList;
	}

}
