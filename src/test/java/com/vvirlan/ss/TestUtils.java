package com.vvirlan.ss;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.StockType;
import com.vvirlan.ss.service.StockService;

public class TestUtils {

	public static void createStocks(final StockService stockService) {

		final List<Stock> stocks = Arrays.asList(new Stock("TEA", StockType.COMMON, 0, new BigDecimal("0"), 100L),
				new Stock("POP", StockType.COMMON, 8, new BigDecimal("0"), 100L),
				new Stock("ALE", StockType.COMMON, 23, new BigDecimal("0"), 60L),
				new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal("0.02"), 100L), // 2%
				new Stock("JOE", StockType.COMMON, 13, new BigDecimal("0"), 250L));
		stocks.stream().forEach((s) -> stockService.saveStock(s));

	}

	public static long getMinutesAsMs(final int minutes) {
		return minutes * 1000 * 60;
	}
}
