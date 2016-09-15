package com.vvirlan.ss.service;

import java.math.BigDecimal;

import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.StockType;
import com.vvirlan.ss.repository.StockRepository;

public class StockServiceImpl implements StockService {
	private final StockRepository stockRepository;

	public StockServiceImpl(final StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public void createStock(final String stockSymbol, final String type, final long lastDividend,
			final BigDecimal fixedDividend, final long parValue) {
		final Stock newStock = new Stock(stockSymbol, StockType.valueOf(type), lastDividend, fixedDividend, parValue);
		stockRepository.persistStock(newStock);
	}

}
