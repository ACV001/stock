package com.vvirlan.ss;

import java.math.BigDecimal;

import com.vvirlan.ss.service.StockService;

public class TestUtils {

	public static void createStocks(final StockService stockService) {
		stockService.createStock("TEA", "COMMON", 0, new BigDecimal("0"), 100L);
		stockService.createStock("POP", "COMMON", 8, new BigDecimal("0"), 100L);
		stockService.createStock("ALE", "COMMON", 23, new BigDecimal("0"), 60L);
		stockService.createStock("GIN", "PREFERRED", 8, new BigDecimal("0.02"), 100L); // 2%
		stockService.createStock("JOE", "COMMON", 13, new BigDecimal("0"), 250L);
	}

	public static long getMinutesAsMs(final int minutes) {
		return minutes * 1000 * 60;
	}
}
