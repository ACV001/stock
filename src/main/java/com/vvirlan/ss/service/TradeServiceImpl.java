package com.vvirlan.ss.service;

import java.util.Collection;
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
	public void recordTrade(final Trade trade) {
		if (trade == null) {
			throw new IllegalArgumentException("Trade cannot be null");
		}
		// No further validations needed because Trade is an immutable class
		// and all validations happen in Trade constructor
		tradeRepository.recordTrade(trade);
	}

	@Override
	public Collection<Trade> getAllTrades() {

		return tradeRepository.getAllTrades();
	}

	@Override
	public List<Trade> findTradeBySymbol(final String stockSymbol) {
		return tradeRepository.findTradesBySymbol(stockSymbol);
	}

	@Override
	public List<Trade> findTradesInPastMinutes(final int pastMinutes) {
		if (pastMinutes <= 0) {
			throw new IllegalArgumentException("Past minutes must be >=0 !");
		}
		return tradeRepository.findTradesInPastMinutes(pastMinutes);
	}

}
