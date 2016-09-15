package com.vvirlan.ss.repository;

import java.util.List;

import com.vvirlan.ss.model.Trade;

/**
 * In Memory based repository
 *
 * @author A
 *
 */
public class InMemoryTradeRepository implements TradeRepository {

	@Override
	public boolean recordTrade(final Trade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Trade> getAllTrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trade> findTradesInPastMinutes(final int pastMinutes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trade findTradeBySymbol(final String stockSymbol) {
		// TODO Auto-generated method stub
		return null;
	}

}
