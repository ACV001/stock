package com.vvirlan.ss;

import com.vvirlan.ss.controller.SimpleStockController;
import com.vvirlan.ss.repository.StockRepository;
import com.vvirlan.ss.repository.TradeRepository;
import com.vvirlan.ss.service.DividendCalculationService;
import com.vvirlan.ss.service.StockService;
import com.vvirlan.ss.service.TradeService;

public abstract class ControllerFactory {

	protected SimpleStockController instance;

	public ControllerFactory() {
		final TradeRepository tradeRepo = getTradeRepositoryInstance();
		final StockRepository stockRepo = getStockRepositoryInstance();
		final TradeService tradeService = getTradeService(tradeRepo);
		final StockService stockService = getStockService(stockRepo);
		final DividendCalculationService dividendCalculatorService = getDividendCalculatorService(stockRepo);
		instance = getController(tradeService, dividendCalculatorService, stockService);

	}

	public abstract StockService getStockService(StockRepository stockRepo);

	public abstract StockRepository getStockRepositoryInstance();

	public abstract SimpleStockController getController(final TradeService tradeService,
			final DividendCalculationService dividendCalculatorService, StockService stockService);

	public abstract TradeRepository getTradeRepositoryInstance();

	public abstract TradeService getTradeService(TradeRepository repo);

	public abstract DividendCalculationService getDividendCalculatorService(StockRepository stockRepository);

	public abstract SimpleStockController getController();
}
