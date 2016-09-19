package com.vvirlan.ss.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.vvirlan.ss.exception.StockNotFoundException;
import com.vvirlan.ss.exception.ZeroDividendYieldException;
import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.StockType;
import com.vvirlan.ss.repository.StockRepository;

/**
 * The actual business logic is here
 *
 * @author A
 *
 */
public class DividendCalculationServiceImpl implements DividendCalculationService {

	private final StockRepository stockRepository;

	public DividendCalculationServiceImpl(final StockRepository stockRepository) {
		super();
		this.stockRepository = stockRepository;
	}

	@Override
	public BigDecimal calculateDividendYield(final String stockSymbol, final BigDecimal price)
			throws StockNotFoundException {
		validateStockSymbolAndPrice(stockSymbol, price);

		// 1. Find first the Stock by stockName
		final Stock stock = stockRepository.findStock(stockSymbol);
		// We'll be throwing exceptions from here rather than from repository
		// layer. Repository layer will be throwing persistence related
		// exceptions if any
		if (stock == null) {
			throw new StockNotFoundException("Stock with that stockSymbol (" + stockSymbol + ") was not found!");
		}
		BigDecimal result = null;
		if (StockType.COMMON.equals(stock.getType())) {
			final BigDecimal lastDividend = new BigDecimal(stock.getLastDividend());
			result = lastDividend.divide(price, 4, RoundingMode.HALF_EVEN);
		} else {
			final BigDecimal fixedDividend = stock.getFixedDividend();
			final BigDecimal parValue = new BigDecimal(stock.getParValue());
			result = fixedDividend.multiply(parValue).divide(price, 4, RoundingMode.HALF_EVEN);
		}
		return result;
	}

	@Override
	public BigDecimal calculatePeRatio(final String stockSymbol, final BigDecimal price)
			throws StockNotFoundException, ZeroDividendYieldException {
		validateStockSymbolAndPrice(stockSymbol, price);
		final BigDecimal dividendYield = calculateDividendYield(stockSymbol, price);
		if (dividendYield.compareTo(BigDecimal.ZERO) == 0) {
			throw new ZeroDividendYieldException("Dividend Yield is zero. Cannot calculate P/E ratio");
		}
		return price.divide(dividendYield, 4, RoundingMode.HALF_EVEN);
	}

	@Override
	public BigDecimal calculateGeometricMean(final List<BigDecimal> prices) {

		if (prices == null || prices.isEmpty()) {
			throw new IllegalArgumentException("The list of prices cannot be null nor empty!");
		}

		final int n = prices.size();
		final BigDecimal product = prices.stream().reduce(new BigDecimal("1"), (x, y) -> x.multiply(y));
		final double pow = 1 / (double) n;
		final double result = Math.pow(product.doubleValue(), pow);
		return BigDecimal.valueOf(result);
	}

	@Override
	public BigDecimal calculateVolumeWeightedStockPrice(final String stockName, final List<BigDecimal> tradedPrices,
			final List<Long> qty) {

		if (tradedPrices == null || tradedPrices.isEmpty()) {
			throw new IllegalArgumentException("Traded prices cannot be null nor empty");
		}

		if (qty == null || qty.isEmpty()) {
			throw new IllegalArgumentException("Qtys cannot be null nor empty");
		}

		final Long sumQty = qty.stream().mapToLong(Long::longValue).sum();
		BigDecimal total = new BigDecimal("0");
		for (int i = 0; i < tradedPrices.size(); i++) {
			total = total.add(tradedPrices.get(i).multiply(BigDecimal.valueOf(qty.get(i))));
		}
		return total.divide(BigDecimal.valueOf(sumQty), 4, RoundingMode.HALF_EVEN);
	}

	private void validateStockSymbolAndPrice(final String stockSymbol, final BigDecimal price) {
		if (stockSymbol == null || stockSymbol.isEmpty()) {
			throw new IllegalArgumentException("Stock Symbol cannot be null nor empty!");
		}

		if (price == null || price.compareTo(BigDecimal.ZERO) == 0) {
			throw new IllegalArgumentException("Price cannot be null nor zero!");
		}
	}

}
