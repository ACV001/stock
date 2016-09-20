package com.vvirlan.ss;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.vvirlan.ss.controller.SimpleStockController;
import com.vvirlan.ss.exception.StockNotFoundException;
import com.vvirlan.ss.exception.ZeroDividendYieldException;

/**
 * Entry point to test the app
 * @author vvirlan
 *
 */
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
		//Create some stocks
		setupStocks(controller);
		//Create some trades
		setupTrades(controller);
		// Emulate multiple parallel requests...
		while (true) {
			// calculateDividendYield
			final Thread th = new Thread(() -> {
				Future<BigDecimal> result = null;
				try {
					final ThreadLocalRandom rand = ThreadLocalRandom.current();

					result = controller.calculateDividendYield("POP",
							new BigDecimal(String.valueOf(rand.nextDouble())));

					System.out.println("Dividend yield " + Thread.currentThread().getName() + " = " + result.get());
				} catch (InterruptedException | ExecutionException | StockNotFoundException e) {
					e.printStackTrace();
				}

			});
			th.start();

			// =====calculatePeRatio======
			final Thread th2 = new Thread(() -> {
				Future<BigDecimal> result = null;
				try {
					final ThreadLocalRandom rand = ThreadLocalRandom.current();
					result = controller.calculatePeRatio("POP", new BigDecimal(String.valueOf(rand.nextDouble())));

					System.out.println("P/E Ratio " + Thread.currentThread().getName() + " = " + result.get());
				} catch (InterruptedException | ExecutionException | StockNotFoundException
						| ZeroDividendYieldException e) {
					e.printStackTrace();
				}

			});
			th2.start();

			// ======recordTrade=====
			final Thread th3 = new Thread(() -> {
				final ThreadLocalRandom rand = ThreadLocalRandom.current();
				controller.recordTrade("POP", new Date().getTime(), rand.nextLong(1000),
						new BigDecimal(String.valueOf(rand.nextDouble())), "BUY");
				System.out.println("Trade recorded " + Thread.currentThread().getName());

			});
			th3.start();

			// ======calculateVolumeWeightedStockPrice=====
			final Thread th4 = new Thread(() -> {
				Future<BigDecimal> result = null;
				result = controller.calculateVolumeWeightedStockPrice("POP");
				try {
					System.out.println("calculateVolumeWeightedStockPrice " + Thread.currentThread().getName() + " = " + result.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			th4.start();

			// ===========calculateAllShareIndex===
			final Thread th5 = new Thread(() -> {
				Future<BigDecimal> result = null;
				result = controller.calculateAllShareIndex();
				try {
					System.out.println("All share index " + Thread.currentThread().getName() + " = " + result.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			th5.start();

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

	private void setupTrades(final SimpleStockController controller) {
		controller.recordTrade("POP", new Date().getTime(), 500L, new BigDecimal("33.44"), "BUY");
		controller.recordTrade("GIN", new Date().getTime(), 320L, new BigDecimal("30.44"), "SELL");
		controller.recordTrade("JOE", new Date().getTime(), 100L, new BigDecimal("3.44"), "BUY");
	}
}
