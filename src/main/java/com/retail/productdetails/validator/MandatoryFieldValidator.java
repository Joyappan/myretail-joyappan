package com.retail.productdetails.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.retail.productdetails.util.DbConnectionUtil;
import com.retail.productdetails.view.ProductDetailsRequest;

/**
 * MandatoryFieldValidator class takes care of validation of input methods which are mandatory.
 * @author Joyappan
 *
 */
public class MandatoryFieldValidator implements ConstraintValidator<MandatoryField, ProductDetailsRequest>   {

	private static final Logger LOGGER = LoggerFactory.getLogger(MandatoryFieldValidator.class);
	
	@Override
	public void initialize(MandatoryField arg0) {
		LOGGER.error("Initialiozing the valiodator class");
	}

	/**
	 * This method validates and see whether we have all required fields are in the request.
	 * If not it will return a false.
	 */
	@Override
	public boolean isValid(ProductDetailsRequest productDetailsRequest, ConstraintValidatorContext arg1) {

		boolean isValidRequest = true;

		if (productDetailsRequest == null) {
			LOGGER.error("Madatory check for ProductDetailsRequest is failed as the ProductDetailsRequest is null");
			isValidRequest = false;
		}

		if (productDetailsRequest.getProductPrice() == null) {
			LOGGER.error("Madatory check for ProductPriceRequest is failed as the ProductPriceRequest is null");
			isValidRequest = false;
		}

		return isValidRequest;
	}
	
}
