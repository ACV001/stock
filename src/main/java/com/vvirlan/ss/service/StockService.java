package com.vvirlan.ss.service;

import java.math.BigDecimal;

import com.vvirlan.ss.model.Stock;

public interface StockService {

	public void createStock(String stockSymbol, String type, long lastDividend, BigDecimal fixedDividend,
			long parValue);

	public Stock findStock(final String stockSymbol);

}
