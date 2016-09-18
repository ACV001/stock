package com.vvirlan.ss.controller;

import java.math.BigDecimal;
import java.util.concurrent.Future;

import com.vvirlan.ss.StockNotFoundException;

public interface SimpleStockController {

	Future<BigDecimal> calculateDividendYield(String stockSymbol, BigDecimal price) throws StockNotFoundException;

	public void createStock(String stockSymbol, String type, long lastDividend, BigDecimal fixedDividend,
			long parValue);

}