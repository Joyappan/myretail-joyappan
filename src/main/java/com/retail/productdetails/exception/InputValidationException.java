package com.retail.productdetails.exception;


/**
 * 
 * Class  defines InputValidationException.
 * @author Joyappan
 *
 */
public class InputValidationException extends RuntimeException {

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
	public InputValidationException(String code, String message) {
		super(message);
		this.code = code;
	}

}
