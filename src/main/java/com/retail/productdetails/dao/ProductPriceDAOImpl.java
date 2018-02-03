package com.retail.productdetails.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.retail.productdetails.config.PropertyReader;
import com.retail.productdetails.data.ProductPrice;
import com.retail.productdetails.exception.ApplicationErrorCode;
import com.retail.productdetails.exception.ProductException;
import com.retail.productdetails.util.DbConnectionUtil;

/**
 * DOA Implementation class for ProductPriceDAO. This class includes the
 * required functionality and business logic while fetching data from DB.
 * 
 * @author Joyappan
 *
 */
@Component
public class ProductPriceDAOImpl implements ProductPriceDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceDAOImpl.class);

	@Autowired
	PropertyReader propertyReader;

	@Autowired
	DbConnectionUtil dbConnectionUtil;

	/**
	 * Method fetches the price for a product based on product id. This will
	 * respond with ProductPrice object and will contains regular price,
	 * clearance price, price type indicator and Currency code.
	 * 
	 * @param productId
	 * @return ProductPrice
	 */
	@Override
	public ProductPrice getProductPrice(int productId) {
		try {
			CassandraOperations cassandraOperations = dbConnectionUtil.getDBConnection();
			final String[] columns = new String[] { propertyReader.getCassandraColumnlist() };
			Select select = QueryBuilder.select(columns).from(propertyReader.getCassandraTablename());
			select.where(QueryBuilder.eq(propertyReader.getCassandraColumnid(), productId));
			List<ProductPrice> priceList = cassandraOperations.select(select, ProductPrice.class);
			if (priceList != null && !priceList.isEmpty()) {
				return priceList.get(0);
			} else {
				throw new ProductException(ApplicationErrorCode.DATABASE_PRICENOTFOUND_ERROR,
						"Price for the item not available in DB for the item " + productId);
			}
		} catch (DataAccessException e) {
			LOGGER.error("Error while accessing Product price from DB : " + e.getMessage());
			throw new ProductException(ApplicationErrorCode.DATABASE_FAILURE_ERROR,
					"Error while accessing product price");
		}

	}

	/**
	 * Method performs the DB update based on the input pricing object.
	 * 
	 * @param ProductPrice
	 * @return
	 */
	@Override
	public void updateProductPrice(ProductPrice productPrice) {
		try {
			CassandraOperations cassandraOperations = dbConnectionUtil.getDBConnection();
			cassandraOperations.execute(updateQueryBuilder(productPrice));
		} catch (DataAccessException e) {
			LOGGER.error("Error while updating price information to DB for the item : " + productPrice.getId());
			LOGGER.debug("Error caused by " + e.getMessage());
			throw new ProductException(ApplicationErrorCode.DATABASE_FAILURE_ERROR,
					"Error while updating price information to DB for the item");
		}
	}

	/**
	 * Method to generate Update query string based on the input Pricing Object.
	 */
	public String updateQueryBuilder(ProductPrice productPrice) {

		boolean isFirst = true;
		StringBuilder udpateQuery = new StringBuilder();
		udpateQuery.append("UPDATE PRODUCT_PRICE SET ");

		if (productPrice.getRegularPrice() != null && productPrice.getRegularPrice().doubleValue() > 0) {
			udpateQuery.append(" REGULAR_PRICE = " + productPrice.getRegularPrice().doubleValue());
			isFirst = false;
		}
		if (productPrice.getClearancePrice() != null && productPrice.getClearancePrice().doubleValue() > 0) {
			if (!isFirst) {
				udpateQuery.append(", ");
			}
			udpateQuery.append(" CLEARANCE_PRICE = " + productPrice.getClearancePrice().doubleValue());
			isFirst = false;
		}
		if (productPrice.getCurrencyType() != null && !productPrice.getCurrencyType().isEmpty()) {
			if (!isFirst) {
				udpateQuery.append(", ");
			}
			udpateQuery.append(" CURRENCY='" + productPrice.getCurrencyType() + "'");
			isFirst = false;
		}
		if (productPrice.getPriceType() != null && !productPrice.getPriceType().isEmpty()) {
			if (!isFirst) {
				udpateQuery.append(", ");
			}
			udpateQuery.append(" PRICE_TYPE = '" + productPrice.getPriceType() + "'");
			isFirst = false;
		}
		if (!isFirst) {
			udpateQuery.append(", ");
		}
		udpateQuery.append(" LAST_UPDATED_TIME='" + getCurrentTime() + "'");
		udpateQuery.append(" WHERE PRODUCT_PRICE_ID=" + productPrice.getId() + " IF EXISTS");
		return udpateQuery.toString();

	}

	/**
	 * Method to return current time stamp based on server time in 'yyyy-MM-dd
	 * HH:mm:ssZ' format
	 * 
	 * @return
	 */
	private String getCurrentTime() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
		String formattedDate = df.format(date);
		LOGGER.debug("Current time stamp" + formattedDate);
		return formattedDate;

	}
}
