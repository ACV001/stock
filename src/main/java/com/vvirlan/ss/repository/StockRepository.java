package com.vvirlan.ss.repository;

import java.math.BigDecimal;
import java.util.List;

import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.StockType;

public interface StockRepository {

	/**
	 * Finds a {@link Stock} by stock symbol
	 *
	 * @param stockSymbol
	 *            a String representing the Stock Symbol
	 * @return a {@link Stock} that was found otherwise it returns null
	 */
	public Stock findStock(String stockSymbol);

	/**
	 * Save the stock to storage
	 *
	 * @param stock
	 */
	public void persistStock(Stock stock);

	/**
	 * Find a stock by type
	 *
	 * @param stockType
	 * @return
	 */
	public List<Stock> getStocksByType(StockType stockType);

	/**
	 * Find stock by Last Dividend
	 *
	 * @param lastDividend
	 * @return
	 */
	public List<Stock> getStocksByLastDividend(Long lastDividend);

	/**
	 * Find stock by fixed dividend
	 *
	 * @param fixedDividend
	 * @return
	 */
	public List<Stock> getStocksByFixedDividend(BigDecimal fixedDividend);

	/**
	 * Find stock by par value
	 *
	 * @param parValue
	 * @return
	 */
	public List<Stock> getStocksByParValue(Long parValue);
}
