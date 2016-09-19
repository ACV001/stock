package com.vvirlan.ss.service;

import java.math.BigDecimal;
import java.util.List;

import com.vvirlan.ss.exception.StockNotFoundException;
import com.vvirlan.ss.exception.ZeroDividendYieldException;

public interface DividendCalculationService {

	/**
	 * Calculate Dividend Yield given a {@code Stock} and the price
	 *
	 * @param stockName
	 *            {@code String} the name of the stock to calculate the dividend
	 *            yield for
	 * @param price
	 *            {@code Stock} price. Assumption: price is in dollars
	 * @return the calculated dividend yield in dollars
	 * @throws StockNotFoundException
	 *             if the stock was not found
	 */
	public BigDecimal calculateDividendYield(String stockName, BigDecimal price) throws StockNotFoundException;

	/**
	 * Calculates the P/E Ratio according to this formulae: P/E Ration = Price /
	 * Dividend
	 *
	 * @param stockSymbol
	 *            {@code String} to calculate P/E for
	 * @param price
	 *            the price. Assumption: price is in dollars
	 * @return P/E ratio
	 * @throws StockNotFoundException
	 * @throws ZeroDividendYieldException
	 */
	public BigDecimal calculatePeRatio(String stockName, BigDecimal price)
			throws StockNotFoundException, ZeroDividendYieldException;

	/**
	 * Calculates the geometric mean of the GBCE All Share index
	 *
	 * @param prices
	 *            the prices
	 * @return the Geometric Mean
	 */
	public BigDecimal calculateGeometricMean(List<BigDecimal> prices);

	/**
	 * Calculates the Volume Weighted Stock Price
	 *
	 * @param tradedPrices
	 *            A {@code java.util.List} of traded prices
	 * @param qty
	 *            A {@code java.util.List} of Quantities
	 * @return the Volume Weighted Stock Price
	 */
	public BigDecimal calculateVolumeWeightedStockPrice(List<BigDecimal> tradedPrices, List<Integer> qty);

}
