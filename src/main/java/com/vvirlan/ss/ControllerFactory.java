package com.vvirlan.ss;

import com.vvirlan.ss.controller.SimpleStockController;
import com.vvirlan.ss.repository.StockRepository;
import com.vvirlan.ss.repository.TradeRepository;
import com.vvirlan.ss.service.DividendCalculationService;
import com.vvirlan.ss.service.StockService;
import com.vvirlan.ss.service.TradeService;

public abstract class ControllerFactory {

	protected SimpleStockController stockControllerInstance;

	public ControllerFactory() {
		final TradeRepository tradeRepo = getTradeRepositoryInstance();
		final StockRepository stockRepo = getStockRepositoryInstance();
		final TradeService tradeService = getTradeService(tradeRepo);
		final StockService stockService = getStockService(stockRepo);
		final DividendCalculationService dividendCalculatorService = getDividendCalculatorService(stockRepo);
		stockControllerInstance = getController(tradeService, dividendCalculatorService, stockService);

	}

	protected abstract StockService getStockService(StockRepository stockRepo);

	protected abstract StockRepository getStockRepositoryInstance();

	protected abstract SimpleStockController getController(final TradeService tradeService,
			final DividendCalculationService dividendCalculatorService, StockService stockService);

	protected abstract TradeRepository getTradeRepositoryInstance();

	protected abstract TradeService getTradeService(TradeRepository repo);

	protected abstract DividendCalculationService getDividendCalculatorService(StockRepository stockRepository);

	protected abstract SimpleStockController getController();
}
