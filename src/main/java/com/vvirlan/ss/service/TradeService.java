package com.vvirlan.ss.service;

import java.util.List;

import com.vvirlan.ss.model.Trade;

public interface TradeService {

	boolean recordTrade(Trade trade);

	List<Trade> getAllTrades();

	List<Trade> findTradeBySymbol(String stockSymbol);

	List<Trade> findTradesInPastMinutes(int pastMinutes);
}
