package com.vvirlan.ss;

public class StockNotFoundException extends Exception {

	private static final long serialVersionUID = -6223501234352320383L;

	public StockNotFoundException(final String msg) {
		super(msg);
	}

}
