package com.retail.productdetails.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.retail.productdetails.view.ProductDetailsExceptionResponse;

/**
 * Error handling controller.
 * All the exception scenarios are caught in this class and handles gracefully.
 * 
 * Even though application need t o handle more exceptions , we can add handler to each of those.
 * 
 * @author Joyappan
 *
 */
@ControllerAdvice
public class ErrorHandlingController {

	/**
	 * Exception handler for the generic exception.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProductDetailsExceptionResponse> generalException(Exception exception){
		ProductDetailsExceptionResponse pdExceptionResponse  = new ProductDetailsExceptionResponse();
		pdExceptionResponse.setCode(ApplicationErrorCode.APPLICATION_FAILURE_ERROR);
		if(exception.getCause()!=null && exception.getCause().getMessage()!=null){
			pdExceptionResponse.setMessage(exception.getCause().getMessage());
		}else{
			pdExceptionResponse.setMessage(exception.getMessage());
		}
		
		return new ResponseEntity<ProductDetailsExceptionResponse>(pdExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * This method takes care of handlkng all ProductExceptions returned from the application.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ProductDetailsExceptionResponse> productDetailsExceptionResponseException(ProductException exception){
		ProductDetailsExceptionResponse pdExceptionResponse  = new ProductDetailsExceptionResponse();
		pdExceptionResponse.setCode(exception.getCode());
		pdExceptionResponse.setMessage(exception.getMessage());
		
		return new ResponseEntity<ProductDetailsExceptionResponse>(pdExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * input validation by application is handled in this section.
	 * @param exception
	 * @return
	 * 
	 */
	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<ProductDetailsExceptionResponse> inputValidationExceptionResponseException(InputValidationException exception){
		ProductDetailsExceptionResponse pdExceptionResponse  = new ProductDetailsExceptionResponse();
		pdExceptionResponse.setCode(exception.getCode());
		pdExceptionResponse.setMessage(exception.getMessage());
		
		return new ResponseEntity<ProductDetailsExceptionResponse>(pdExceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method is to handle MethodArgumentNotValidException gracefully.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ProductDetailsExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception){
		ProductDetailsExceptionResponse pdExceptionResponse  = new ProductDetailsExceptionResponse();
		pdExceptionResponse.setCode(ApplicationErrorCode.INPUT_VALIDATION_ERROR);
		pdExceptionResponse.setMessage("One or more required input parameters are missing.");
		return new ResponseEntity<ProductDetailsExceptionResponse>(pdExceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method is to handle HttpMessageNotReadableException gracefully.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ProductDetailsExceptionResponse> httpMessageNotReadableException(HttpMessageNotReadableException exception){
		ProductDetailsExceptionResponse pdExceptionResponse  = new ProductDetailsExceptionResponse();
		pdExceptionResponse.setCode(ApplicationErrorCode.INPUT_READ_ERROR);
		pdExceptionResponse.setMessage("Invalid formart in the request");
		return new ResponseEntity<ProductDetailsExceptionResponse>(pdExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
