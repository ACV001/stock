package com.vvirlan.ss;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.vvirlan.ss.controller.SimpleStockController;
import com.vvirlan.ss.exception.StockNotFoundException;

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

		final ControllerFactory factory = InMemoryControllerFactory.getInstance();
		final SimpleStockController controller = factory.getController();

		setupStocks(controller);
		//Emulate multiple parallel requests...
		while (true) {

			// calculateDividendYield
			final Thread th = new Thread(() -> {
				Future<BigDecimal> result = null;
				try {
					result = controller.calculateDividendYield("POP", new BigDecimal("22.3"));

					System.out.println("Dividend yield " + Thread.currentThread().getName() + " = " + result.get());
				} catch (InterruptedException | ExecutionException | StockNotFoundException e) {
					e.printStackTrace();
				}

			});
			th.start();

			//===========
			final Thread th2 = new Thread(() -> {
				Future<BigDecimal> result = null;
				try {
					result = controller.calculateDividendYield("POP", new BigDecimal("22.3"));

					System.out.println("Dividend yield " + Thread.currentThread().getName() + " = " + result.get());
				} catch (InterruptedException | ExecutionException | StockNotFoundException e) {
					e.printStackTrace();
				}

			});
			th2.start();






			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void setupStocks(final SimpleStockController controller) {
		controller.createStock("TEA", "COMMON", 0, new BigDecimal("0"), 100L);
		controller.createStock("POP", "COMMON", 8, new BigDecimal("0"), 100L);
		controller.createStock("ALE", "COMMON", 23, new BigDecimal("0"), 60L);
		controller.createStock("GIN", "PREFERRED", 8, new BigDecimal("0.02"), 100L); // 2%
		controller.createStock("JOE", "COMMON", 13, new BigDecimal("0"), 250L);

	}
}
