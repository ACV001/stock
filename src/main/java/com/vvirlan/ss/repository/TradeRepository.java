package com.vvirlan.ss.repository;

import java.util.Collection;
import java.util.List;

import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.Trade;

/**
 * The Trades repository. Has low level operations on Trade
 * @author vvirlan
 *
 */
public interface TradeRepository {

	/**
	 * Records a new {@code Trade}
	 *
	 * @param trade
	 *            a {@code Trade} object. Must not be {@code null} or
	 *            {@code IllegalArgumentException} will be thrown
	 * @return {@code true} if the operation succeeded
	 */
	void recordTrade(Trade trade);

	/**
	 * Get all the recorded trades
	 *
	 * @return a collection of trades. Never null
	 */
	Collection<Trade> getAllTrades();

	/**
	 * Find a specific {@code Trade} by symbol
	 *
	 * @param stockSymbol
	 *            a {@code String} representing the stock symbol to search the
	 *            trade by
	 * @return a List of {@code Trade} found or if not found an empty list
	 */
	List<Trade> findTradesBySymbol(String stockSymbol);

	/**
	 * Finds all trades that have their time-stamp in the range of the past
	 * {@code pastMinutes}
	 *
	 * @param pastMinutes
	 *            the number of minutes to go back to find the {@code Trade}
	 * @param stock
	 *            the stock which trades are being looked for
	 * @return a List of {@code Trade}s found or empty list. Never null
	 */
	List<Trade> findTradesInPastMinutes(Stock stock, int pastMinutes);
}
