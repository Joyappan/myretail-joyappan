package com.retail.productdetails.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.productdetails.data.ProductDetails;
import com.retail.productdetails.exception.ApplicationErrorCode;
import com.retail.productdetails.exception.ProductException;

/**
 * 
 * This class to support parsing Jason string response to Product details
 * object.
 * 
 * @Author Joyappan
 */

@Component
public class JsonParserUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonParserUtil.class);

	private static String NODE_PRODUCT_DESCRIPTION = "product_description";
	private static String NODE_PRODUCT = "product";
	private static String NODE_ITEM = "item";
	private static String NODE_UPC = "upc";
	private static String NODE_TITLE = "title";
	private static String NODE_TCIN = "tcin";

	/**
	 * Method takes care of converting JSON  String to  ProductDetails based on the given nodes. 
	 * @param productGatewayResponse
	 * @return
	 */
	public ProductDetails parseJsonResponse(String productGatewayResponse) {

		LOGGER.debug("Start parsing the JSON string  : " +productGatewayResponse );
		ProductDetails productDetails = new ProductDetails();
		try {

			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> map = new HashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(productGatewayResponse, new TypeReference<Map<String, Map>>() {
			});
			Map<String, Object> product = (Map<String, Object>) map.get(NODE_PRODUCT);
			if (product != null && !product.isEmpty()) {
				Map<String, Object> item = (Map<String, Object>) product.get(NODE_ITEM);
				String tcin = (String) item.get(NODE_TCIN);
				if (tcin != null && !tcin.isEmpty()) {
					try {
						productDetails.setProductId(Integer.parseInt(tcin));
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("Error while getting product id"+ e.getMessage());
					}
				}
				String upc = (String) item.get(NODE_UPC);
				if (upc != null && !upc.isEmpty()) {
					productDetails.setUpc(upc);
				}
				if (item != null && !item.isEmpty()) {
					Map<String, Object> productDescription = (Map<String, Object>) item.get(NODE_PRODUCT_DESCRIPTION);
					String title = (String) productDescription.get(NODE_TITLE);
					if (title != null) {
						productDetails.setProductDisplayName(title);
					}
				}
			} else {
				LOGGER.error("Product details not available for setting in response object.");
				throw new ProductException(ApplicationErrorCode.PRODUCTGATEWAY_PRODUCTNOTAVAILABLE_ERROR,
						"Product not avalable");
			}
		} catch (Exception e) {
			LOGGER.error("Exception while parsing the json response ", e.getMessage());
			throw new ProductException(ApplicationErrorCode.PRODUCTGATEWAY_RESPONSEPARSING_ERROR,
					"Exception while parsing the response" );
		}

		return productDetails;
	}

}
