package com.vvirlan.ss.controller;

import java.math.BigDecimal;

import com.vvirlan.ss.StockNotFoundException;
import com.vvirlan.ss.service.DividendCalculationService;
import com.vvirlan.ss.service.StockService;
import com.vvirlan.ss.service.TradeService;

/**
 * Application Controller. Entry point. Use this class to run sample tests
 * besides the unit tests provided
 *
 * @author A
 *
 */
public class SimpleStockControllerImpl implements SimpleStockController {
	private final DividendCalculationService dividendService;
	private final TradeService tradeService;
	private final StockService stockService;

	public SimpleStockControllerImpl(final DividendCalculationService dividendService, final TradeService tradeService,
			final StockService stockService) {
		super();
		this.dividendService = dividendService;
		this.tradeService = tradeService;
		this.stockService = stockService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.vvirlan.ss.controller.SimpleStockControllerIf#calculateDividendYield(
	 * java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public BigDecimal calculateDividendYield(final String stockSymbol, final BigDecimal price)
			throws StockNotFoundException {
		return dividendService.calculateDividendYield(stockSymbol, price);

	}

	public static void main(final String[] args) {

	}

	@Override
	public void createStock(final String stockSymbol, final String type, final long lastDividend,
			final BigDecimal fixedDividend, final long parValue) {

		stockService.createStock(stockSymbol, type, lastDividend, fixedDividend, parValue);
	}
}
