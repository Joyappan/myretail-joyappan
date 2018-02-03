package com.retail.productdetails.exception;

/**
 * Class to hold application error constants.
 * 
 * @author Joyappan
 */
public class ApplicationErrorCode {

	//Product Gateway specific error codes
	public static final String PRODUCTGATEWAY_TIMEOUT_ERROR = "Productgateway.Timeout.Error";
	public static final String PRODUCTGATEWAY_SERVICEFAILURE_ERROR = "Productgateway.Service.Error";
	public static final String PRODUCTGATEWAY_INVALIDRESPONSE_ERROR = "Productgateway.InvalidResponse.Error";
	public static final String PRODUCTGATEWAY_PRODUCTNOTAVAILABLE_ERROR = "Productgateway.ProductNotAvailable.Error";
	public static final String PRODUCTGATEWAY_RESPONSEPARSING_ERROR = "Productgateway.ResponsePrasing.Error";
	
	//Database specific error codes
	public static final String DATABASE_CONNECTIONFAILURE_ERROR = "Database.Connection.Error";
	public static final String DATABASE_CONNECTIONCLOSE_ERROR = "Database.ConnectionCloseing.Error";
	public static final String DATABASE_FAILURE_ERROR = "Database.Failure.Error";
	public static final String DATABASE_PRICENOTFOUND_ERROR = "Database.PriceNotFound.Error";
	
	//General application errors
	public static final String APPLICATION_FAILURE_ERROR = "Product.Service.Error";
	
	//Input validation exception
	public static final String INPUT_VALIDATION_ERROR = "ProductDetails.inputvalidation.error";
	public static final String INPUT_READ_ERROR = "ProductDetails.inputread.error";
}
