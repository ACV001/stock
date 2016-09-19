package com.vvirlan.ss.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Future;

import com.vvirlan.ss.exception.StockNotFoundException;
import com.vvirlan.ss.exception.ZeroDividendYieldException;
import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.Trade;
import com.vvirlan.ss.model.TradeType;

public interface SimpleStockController {

	Future<BigDecimal> calculateDividendYield(String stockSymbol, BigDecimal price) throws StockNotFoundException;

	public BigDecimal calculatePeRatio(String stockName, BigDecimal price)
			throws StockNotFoundException, ZeroDividendYieldException;

	public void createStock(String stockSymbol, String type, long lastDividend, BigDecimal fixedDividend,
			long parValue);

	void recordTrade(final Stock stock, final long timestamp, final long qty, final BigDecimal price,
			final TradeType tradeType);

	List<Trade> findTradesInPastMinutes(int pastMinutes);

	public BigDecimal calculateVolumeWeightedStockPrice(List<BigDecimal> tradedPrices, List<Integer> qty);

	public BigDecimal calculateGeometricMean(List<BigDecimal> prices);

	public BigDecimal calculateAllShareIndex();

}