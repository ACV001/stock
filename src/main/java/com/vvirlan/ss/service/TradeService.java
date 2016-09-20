package com.vvirlan.ss.service;

import java.util.Collection;
import java.util.List;

import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.Trade;

/**
 * Service related to Trades
 * @author vvirlan
 *
 */
public interface TradeService {

	void recordTrade(Trade trade);

	Collection<Trade> getAllTrades();

	List<Trade> findTradeBySymbol(String stockSymbol);

	List<Trade> findTradesInPastMinutes(Stock stock, int pastMinutes);
}
