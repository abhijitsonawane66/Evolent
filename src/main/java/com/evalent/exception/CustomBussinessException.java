
package com.evalent.exception;

@SuppressWarnings("serial")
public class CustomBussinessException extends RuntimeException {
	public CustomBussinessException(String mesg) {
		super(mesg);
	}

}
