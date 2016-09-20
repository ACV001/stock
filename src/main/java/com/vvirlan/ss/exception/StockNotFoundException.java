package com.vvirlan.ss.exception;

/**
 * Custom exception to denote that a stock could not be found by stock symbol
 * @author vvirlan
 *
 */
public class StockNotFoundException extends Exception {

	private static final long serialVersionUID = -6223501234352320383L;

	public StockNotFoundException(final String msg) {
		super(msg);
	}

}
