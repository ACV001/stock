package com.vvirlan.ss.service;

import java.util.Collection;

import com.vvirlan.ss.model.Stock;

public interface StockService {



	public void saveStock(Stock stock);

	public Stock findStock(final String stockSymbol);

	public Collection<Stock> getAllStocks();

}
