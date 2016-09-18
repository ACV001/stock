package com.vvirlan.ss.service;

import org.junit.Before;

import com.vvirlan.ss.TestUtils;
import com.vvirlan.ss.repository.InMemoryStockRepository;
import com.vvirlan.ss.repository.StockRepository;

public class StockServiceImplTest {

	DividendCalculationService service;

	StockService stockService;

	@Before
	public void setUp() {
		final StockRepository stockRepository = new InMemoryStockRepository();
		service = new DividendCalculationServiceImpl(stockRepository);
		stockService = new StockServiceImpl(stockRepository);
		TestUtils.createStocks(stockService);

	}

}
