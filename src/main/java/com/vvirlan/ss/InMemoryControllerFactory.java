package com.vvirlan.ss;

import com.vvirlan.ss.controller.SimpleStockController;
import com.vvirlan.ss.controller.SimpleStockControllerImpl;
import com.vvirlan.ss.repository.InMemoryStockRepository;
import com.vvirlan.ss.repository.InMemoryTradeRepository;
import com.vvirlan.ss.repository.StockRepository;
import com.vvirlan.ss.repository.TradeRepository;
import com.vvirlan.ss.service.DividendCalculationService;
import com.vvirlan.ss.service.DividendCalculationServiceImpl;
import com.vvirlan.ss.service.StockService;
import com.vvirlan.ss.service.StockServiceImpl;
import com.vvirlan.ss.service.TradeService;
import com.vvirlan.ss.service.TradeServiceImpl;

public class InMemoryControllerFactory extends ControllerFactory {

	@Override
	public TradeRepository getTradeRepositoryInstance() {
		return new InMemoryTradeRepository();
	}

	@Override
	public SimpleStockController getController(final TradeService tradeService,
			final DividendCalculationService dividendCalculatorService, final StockService stockService) {
		return new SimpleStockControllerImpl(dividendCalculatorService, tradeService, stockService);

	}

	@Override
	public TradeService getTradeService(final TradeRepository tradeRepository) {
		return new TradeServiceImpl(tradeRepository);
	}

	@Override
	public SimpleStockController getController() {
		return instance;
	}

	@Override
	public StockRepository getStockRepositoryInstance() {
		return new InMemoryStockRepository();
	}

	@Override
	public DividendCalculationService getDividendCalculatorService(final StockRepository stockRepository) {

		return new DividendCalculationServiceImpl(stockRepository);

	}

	@Override
	public StockService getStockService(final StockRepository stockRepo) {

		return new StockServiceImpl(stockRepo);
	}

}
