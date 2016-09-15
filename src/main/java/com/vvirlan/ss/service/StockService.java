package com.vvirlan.ss.service;

import java.math.BigDecimal;

public interface StockService {

	public void createStock(String stockSymbol, String type, long lastDividend, BigDecimal fixedDividend,
			long parValue);
}
