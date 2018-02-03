package com.retail.productdetails.view;
/**
 * Exception response calls.
 * On exception exception handler should use this class to respond back.
 * 
 * @author Joyappan
 *
 */
public class ProductDetailsExceptionResponse {

		private String code;
		private String message;
		

		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
}
