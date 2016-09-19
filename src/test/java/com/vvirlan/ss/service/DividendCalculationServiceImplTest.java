package com.vvirlan.ss.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.vvirlan.ss.TestUtils;
import com.vvirlan.ss.exception.StockNotFoundException;
import com.vvirlan.ss.exception.ZeroDividendYieldException;
import com.vvirlan.ss.repository.InMemoryStockRepository;
import com.vvirlan.ss.repository.StockRepository;

public class DividendCalculationServiceImplTest {

	DividendCalculationService dividendCalculationService;

	StockService stockService;

	@Before
	public void setUp() {
		final StockRepository stockRepository = new InMemoryStockRepository();
		dividendCalculationService = new DividendCalculationServiceImpl(stockRepository);

		stockService = new StockServiceImpl(stockRepository);
		TestUtils.createStocks(stockService);

	}

	@Test
	public void calculateVolumeWeightedStockPricePos() {
		final List<BigDecimal> prices = new ArrayList<>();
		prices.add(new BigDecimal("1.5"));
		prices.add(new BigDecimal("2.5"));

		final List<Long> qtys = new ArrayList<>();
		qtys.add(new Long(2));
		qtys.add(new Long(3));

		final BigDecimal result = dividendCalculationService.calculateVolumeWeightedStockPrice(prices, qtys);
		assertEquals(0, result.compareTo(new BigDecimal("2.1")));

	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateVolumeWeightedStockPriceNeg4() {
		dividendCalculationService.calculateVolumeWeightedStockPrice(Arrays.asList(), Arrays.asList(new Long("1")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateVolumeWeightedStockPriceNeg3() {
		dividendCalculationService.calculateVolumeWeightedStockPrice(Arrays.asList(new BigDecimal("0")), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateVolumeWeightedStockPriceNeg2() {
		dividendCalculationService.calculateVolumeWeightedStockPrice(Arrays.asList(new BigDecimal("0")),
				Arrays.asList());
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateVolumeWeightedStockPriceNeg1() {
		dividendCalculationService.calculateVolumeWeightedStockPrice(null, Arrays.asList(new Long("1")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateVolumeWeightedStockPriceNeg() {
		dividendCalculationService.calculateVolumeWeightedStockPrice(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateGeometricMeanEmpNeg() {
		dividendCalculationService.calculateGeometricMean(new ArrayList<>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateGeometricMeanNeg() {
		dividendCalculationService.calculateGeometricMean(null);
	}

	@Test
	public void calculateGeometricMeanPos() {
		final List<BigDecimal> prices = new ArrayList<>();
		prices.add(new BigDecimal("2.10"));
		prices.add(new BigDecimal("1.10"));
		prices.add(new BigDecimal("20.10"));
		final BigDecimal result = dividendCalculationService.calculateGeometricMean(prices);
		assertEquals(0, result.compareTo(new BigDecimal("3.5942036352372724")));
	}

	@Test
	public void calculatePeRatioPrefPos() throws StockNotFoundException, ZeroDividendYieldException {
		final BigDecimal result = dividendCalculationService.calculatePeRatio("GIN", new BigDecimal("10"));
		final BigDecimal exp = new BigDecimal("50");
		assertEquals(0, exp.compareTo(result));
	}

	@Test(expected = ZeroDividendYieldException.class)
	public void calculatePeRatioCommonZeroNeg() throws StockNotFoundException, ZeroDividendYieldException {
		dividendCalculationService.calculatePeRatio("TEA", new BigDecimal("10"));
	}

	@Test
	public void calculatePeRatioCommonPos() throws StockNotFoundException, ZeroDividendYieldException {
		final BigDecimal result = dividendCalculationService.calculatePeRatio("POP", new BigDecimal("10"));
		final BigDecimal exp = new BigDecimal("12.5");
		assertEquals(0, exp.compareTo(result));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculatePeRatioNeg() throws StockNotFoundException, ZeroDividendYieldException {
		dividendCalculationService.calculatePeRatio(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculatePeRatioNeg2() throws StockNotFoundException, ZeroDividendYieldException {
		dividendCalculationService.calculatePeRatio(null, new BigDecimal("9"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculatePeRatioNeg3() throws StockNotFoundException, ZeroDividendYieldException {
		dividendCalculationService.calculatePeRatio("GIN", new BigDecimal("0"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculatePeRatioNeg4() throws StockNotFoundException, ZeroDividendYieldException {
		dividendCalculationService.calculatePeRatio("", new BigDecimal("10"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculatePeRatioNeg5() throws StockNotFoundException, ZeroDividendYieldException {
		dividendCalculationService.calculatePeRatio("GIN", null);
	}

	/**
	 * FixedDividend * ParValue / Price => (0% * 100 / 10) = 0
	 *
	 * @throws StockNotFoundException
	 */
	@Test
	public void calculateDividendYieldPreferredZeroDiv() throws StockNotFoundException {
		stockService.createStock("ZER", "PREFERRED", 8, new BigDecimal("0"), 100L); // 2%
		final BigDecimal result = dividendCalculationService.calculateDividendYield("ZER", new BigDecimal("10"));
		final BigDecimal exp = new BigDecimal("0");
		assertEquals(0, exp.compareTo(result));
	}

	/**
	 * FixedDividend * ParValue / Price => (2% * 100 / 10) = 0.2
	 *
	 * @throws StockNotFoundException
	 */
	@Test
	public void calculateDividendYieldPreferredTwoDiv() throws StockNotFoundException {
		final BigDecimal result = dividendCalculationService.calculateDividendYield("GIN", new BigDecimal("10"));
		final BigDecimal exp = new BigDecimal("0.2");
		assertEquals(0, exp.compareTo(result));
	}

	@Test
	public void calculateDividendYieldCommonPosZeroDiv() throws StockNotFoundException {
		final BigDecimal result = dividendCalculationService.calculateDividendYield("TEA", new BigDecimal("10"));
		final BigDecimal exp = BigDecimal.ZERO;
		assertEquals(0, exp.compareTo(result));
	}

	@Test
	public void calculateDividendYieldCommonPosDiv() throws StockNotFoundException {
		final BigDecimal result = dividendCalculationService.calculateDividendYield("POP", new BigDecimal("10"));
		final BigDecimal exp = new BigDecimal("0.8");
		assertEquals(0, exp.compareTo(result), 0.0001d);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateDividendYieldZeroPrice() throws StockNotFoundException {
		dividendCalculationService.calculateDividendYield("TEA", new BigDecimal("0"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateDividendYieldNullPrice() throws StockNotFoundException {
		dividendCalculationService.calculateDividendYield("TEA", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateDividendYieldNullStockSymbol() throws StockNotFoundException {
		dividendCalculationService.calculateDividendYield(null, new BigDecimal("5"));
	}

	@Test(expected = StockNotFoundException.class)
	public void calculateDividendYieldException() throws StockNotFoundException {
		dividendCalculationService.calculateDividendYield("NON STOCK", new BigDecimal("5"));
	}

}
