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

/**
 * Part of the Abstract Factory pattern
 * @author vvirlan
 *
 */

public class InMemoryControllerFactory extends ControllerFactory {

	private static ControllerFactory instance;

	public static ControllerFactory getInstance() {
		synchronized (InMemoryControllerFactory.class) {
			if (instance == null) {
				instance = new InMemoryControllerFactory();
			}
		}
		return instance;
	}

	@Override
	protected TradeRepository getTradeRepositoryInstance() {
		return new InMemoryTradeRepository();
	}

	@Override
	protected SimpleStockController getController(final TradeService tradeService,
			final DividendCalculationService dividendCalculatorService, final StockService stockService) {
		return new SimpleStockControllerImpl(dividendCalculatorService, tradeService, stockService);

	}

	@Override
	protected TradeService getTradeService(final TradeRepository tradeRepository) {
		return new TradeServiceImpl(tradeRepository);
	}

	@Override
	protected SimpleStockController getController() {
		return stockControllerInstance;
	}

	@Override
	protected StockRepository getStockRepositoryInstance() {
		return new InMemoryStockRepository();
	}

	@Override
	protected DividendCalculationService getDividendCalculatorService(final StockRepository stockRepository) {

		return new DividendCalculationServiceImpl(stockRepository);

	}

	@Override
	protected StockService getStockService(final StockRepository stockRepo) {

		return new StockServiceImpl(stockRepo);
	}

}
