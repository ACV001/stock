package com.vvirlan.ss.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.vvirlan.ss.exception.StockNotFoundException;
import com.vvirlan.ss.exception.ZeroDividendYieldException;
import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.Trade;
import com.vvirlan.ss.model.TradeType;
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
	private final ExecutorService executor;

	public SimpleStockControllerImpl(final DividendCalculationService dividendService, final TradeService tradeService,
			final StockService stockService) {
		super();
		this.dividendService = dividendService;
		this.tradeService = tradeService;
		this.stockService = stockService;
		executor = Executors.newCachedThreadPool();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.vvirlan.ss.controller.SimpleStockControllerIf#calculateDividendYield(
	 * java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public Future<BigDecimal> calculateDividendYield(final String stockSymbol, final BigDecimal price)
			throws StockNotFoundException {

		final Callable<BigDecimal> task = () -> {
			return dividendService.calculateDividendYield(stockSymbol, price);
		};

		return executor.submit(task);

	}

	public static void main(final String[] args) {

	}

	@Override
	public void createStock(final String stockSymbol, final String type, final long lastDividend,
			final BigDecimal fixedDividend, final long parValue) {
		stockService.createStock(stockSymbol, type, lastDividend, fixedDividend, parValue);
	}

	@Override
	public BigDecimal calculatePeRatio(final String stockName, final BigDecimal price)
			throws StockNotFoundException, ZeroDividendYieldException {

		return null;
	}

	@Override
	public void recordTrade(final Stock stock, final long timestamp, final long qty, final BigDecimal price, final TradeType tradeType) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Trade> findTradesInPastMinutes(final int pastMinutes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculateVolumeWeightedStockPrice(final List<BigDecimal> tradedPrices, final List<Integer> qty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculateGeometricMean(final List<BigDecimal> prices) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculateAllShareIndex() {
		// TODO Auto-generated method stub
		return null;
	}







}
