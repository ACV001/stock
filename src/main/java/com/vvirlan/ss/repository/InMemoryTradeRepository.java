package com.vvirlan.ss.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.vvirlan.ss.model.Trade;

/**
 * In Memory based repository
 *
 * @author A
 *
 */
public class InMemoryTradeRepository implements TradeRepository {
	private final Map<Long, Trade> store = new ConcurrentHashMap<>();

	@Override
	public void recordTrade(final Trade trade) {
		if (trade == null) {
			throw new IllegalArgumentException("Trade to record cannot be null!");
		}
		store.put(trade.getTimestamp(), trade);
	}

	@Override
	public Collection<Trade> getAllTrades() {
		return store.values();
	}

	@Override
	public List<Trade> findTradesInPastMinutes(final int pastMinutes) {
		final Date now = new Date();
		final long pastMinutesAsMs = pastMinutes * 60 * 1000;
		final long delta = now.getTime() - pastMinutesAsMs;
		final List<Trade> tradesInPastMinutes = new ArrayList<>();
		for (final Long timestamp : store.keySet()) {
			if (timestamp >= delta && timestamp <= now.getTime()) {
				tradesInPastMinutes.add(store.get(timestamp));
			}
		}
		return tradesInPastMinutes;
	}

	@Override
	public List<Trade> findTradesBySymbol(final String stockSymbol) {
		final List<Trade> results = new ArrayList<>();
		for (final Trade t : store.values()) {
			if (t.getStock().getSymbol().equals(stockSymbol)) {
				results.add(t);
			}
		}
		return results;
	}

}
