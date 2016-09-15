package com.vvirlan.ss.service;

import java.util.List;

import com.vvirlan.ss.model.Trade;
import com.vvirlan.ss.repository.TradeRepository;

public class TradeServiceImpl implements TradeService {

	private final TradeRepository tradeRepository;

	public TradeServiceImpl(final TradeRepository tradeRepository) {
		super();
		this.tradeRepository = tradeRepository;
	}

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
	public List<Trade> findTradeBySymbol(final String stockSymbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trade> findTradesInPastMinutes(final int pastMinutes) {
		// TODO Auto-generated method stub
		return null;
	}

}
