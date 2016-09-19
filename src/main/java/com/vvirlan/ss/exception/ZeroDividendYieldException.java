package com.vvirlan.ss.exception;

/**
 * Exception will be thrown when Dividend Yield calculation results in zero, but
 * it is required for P/E Ratio calculation as a denominator
 * 
 * @author A
 *
 */
public class ZeroDividendYieldException extends Exception {

	private static final long serialVersionUID = 3070131209760358998L;

	public ZeroDividendYieldException(final String msg) {
		super(msg);
	}

}
