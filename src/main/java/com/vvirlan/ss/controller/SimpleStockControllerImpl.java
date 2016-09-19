package com.vvirlan.ss.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	public Future<BigDecimal> calculatePeRatio(final String stockName, final BigDecimal price)
			throws StockNotFoundException, ZeroDividendYieldException {

		final Callable<BigDecimal> task = () -> {
			return dividendService.calculatePeRatio(stockName, price);
		};

		return executor.submit(task);

	}

	@Override
	public void recordTrade(final String stockSymbol, final long timestamp, final long qty, final BigDecimal price,
			final String tradeType) {
		final Stock stock = stockService.findStock(stockSymbol);
		final Trade trade = new Trade(stock, timestamp, qty, price, TradeType.valueOf(tradeType));
		tradeService.recordTrade(trade);

	}

	@Override
	public List<Trade> findTradesInPastMinutes(final int pastMinutes) {
		return tradeService.findTradesInPastMinutes(pastMinutes);
	}

	@Override
	public Future<BigDecimal> calculateVolumeWeightedStockPrice() {

		final List<Trade> past5MinutesTrades = findTradesInPastMinutes(5);
		final List<BigDecimal> tradePrices = new ArrayList<>();
		final List<Long> qtys = new ArrayList<>();

		if (past5MinutesTrades.isEmpty()) {

		}

		for (final Trade t : past5MinutesTrades) {
			tradePrices.add(t.getPrice());
			qtys.add(t.getQty());
		}

		final Callable<BigDecimal> task = () -> {
			return dividendService.calculateVolumeWeightedStockPrice(tradePrices, qtys);
		};

		return executor.submit(task);
	}

	@Override
	public BigDecimal calculateGeometricMean(final List<BigDecimal> prices) {

		return null;
	}

	@Override
	public BigDecimal calculateAllShareIndex() {

		return null;
	}

}
