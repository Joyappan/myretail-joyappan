/**
 * 
 */
package com.retail.productdetails.exception;

/**
 * Exception class for Product service related exceptions
 * @author Joyappan
 */
public class ProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String code;
	private int status;

	public String getCode() {
		return code;
	}

	public int getStatus() {
		return status;
	}

	/**
	 * ProductException
	 * 
	 * @param code error code
	 * @param message error message description
	 */
	public ProductException(String code, String message) {
		super(message);
		this.code = code;
	}


}
