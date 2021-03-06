package com.vvirlan.ss.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.StockType;

public class InMemoryStockRepository implements StockRepository {

	private final Map<String, Stock> store = new ConcurrentHashMap<>();

	@Override
	public Stock findStock(final String stockSymbol) {
		if (stockSymbol == null) {
			throw new IllegalArgumentException("Stock symbol cannot be null");
		}
		return store.get(stockSymbol);
	}

	@Override
	public void persistStock(final Stock stock) {
		if (stock == null) {
			throw new IllegalArgumentException("Stock cannot be null to persist!");
		}
		if (stock.getSymbol() == null || stock.getSymbol().isEmpty()) {
			throw new IllegalArgumentException("Cannot persist Stock without Stock Symbol!");
		}
		store.put(stock.getSymbol(), stock);

	}

	@Override
	public List<Stock> getStocksByType(final StockType stockType) {

		throw new IllegalStateException("Not Yet Implemented");
	}

	@Override
	public List<Stock> getStocksByLastDividend(final Long lastDividend) {
		throw new IllegalStateException("Not Yet Implemented");
	}

	@Override
	public List<Stock> getStocksByFixedDividend(final BigDecimal fixedDividend) {
		throw new IllegalStateException("Not Yet Implemented");
	}

	@Override
	public List<Stock> getStocksByParValue(final Long parValue) {
		throw new IllegalStateException("Not Yet Implemented");
	}

	@Override
	public Collection<Stock> getAllStocks() {
		return store.values();
	}

}
