package com.vvirlan.ss;

import java.math.BigDecimal;

import com.vvirlan.ss.controller.SimpleStockController;

public class SimpleStockApplication {

	public static void main(final String[] args) {
		new SimpleStockApplication().go();
	}

	/**
	 * App entry point
	 */
	public void go() {

		// 1. Need to bootstrap the application. Instantiate all layers and
		// inject them as needed

		// final DividendCalculationService dividendCalculationService = new
		// DividendCalculationServiceImpl();
		// final TradeRepository tradeRepository = new
		// InMemoryTradeRepository();
		// final TradeService tradeService = new
		// TradeServiceImpl(tradeRepository);
		// final SimpleStockController controller = new
		// SimpleStockController(dividendCalculationService, tradeService);
		// controller.calculateDividendYield(stockSymbol, price)
		final ControllerFactory factory = new InMemoryControllerFactory();
		final SimpleStockController controller = factory.getController();

		controller.createStock("ABS", "COMMON", 2, new BigDecimal("0"), 100L);

		BigDecimal result = null;
		try {
			result = controller.calculateDividendYield("ABS", new BigDecimal("22.3"));
			System.out.println(result);
		} catch (final StockNotFoundException e) {
			e.printStackTrace();
		}

	}
}
