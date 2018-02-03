package com.retail.productdetails.dao;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.productdetails.ProductServiceTestHelper;
import com.retail.productdetails.data.ProductPrice;
import com.retail.productdetails.util.DbConnectionUtil;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:productconfiguration-test.properties")
public class ProductPriceDAOTest {

	ProductServiceTestHelper productServiceTestHelper;

	ProductPrice productPriceInDB;

	@InjectMocks
	ProductPriceDAOImpl productPriceDAO;

	@Mock
	DbConnectionUtil dbConnectionUtil;

	@Mock
	CassandraOperations cassandraOperations;


	List<ProductPrice> productPriceList;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		productServiceTestHelper = new ProductServiceTestHelper();
		productPriceInDB = productServiceTestHelper.getMokedPrice();
	}

	@After
	public void tearDown() {
		productServiceTestHelper = null;
		productPriceInDB = null;
	}

	
	@Test
	public void testUpdateProductPrice(){
		when(dbConnectionUtil.getDBConnection()).thenReturn(cassandraOperations);
		String query = productPriceDAO.updateQueryBuilder(productPriceInDB);
		doNothing().when(cassandraOperations).execute(query);
		productPriceDAO.updateProductPrice(productPriceInDB);
	}

}
