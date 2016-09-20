package com.vvirlan.ss.service;

import java.util.Collection;

import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.repository.StockRepository;

public class StockServiceImpl implements StockService {
	private final StockRepository stockRepository;

	public StockServiceImpl(final StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public Stock findStock(final String stockSymbol) {
		return stockRepository.findStock(stockSymbol);
	}

	@Override
	public void saveStock(final Stock stock) {
		stockRepository.persistStock(stock);
	}

	@Override
	public Collection<Stock> getAllStocks() {
		return stockRepository.getAllStocks();
	}

}
