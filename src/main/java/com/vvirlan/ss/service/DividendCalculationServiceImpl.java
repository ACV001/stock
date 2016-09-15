package com.vvirlan.ss.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.vvirlan.ss.StockNotFoundException;
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
			result = lastDividend.divide(price, 4, RoundingMode.HALF_UP);
		} else {
			final BigDecimal fixedDividend = stock.getFixedDividend();
			final BigDecimal parValue = new BigDecimal(stock.getParValue());
			result = fixedDividend.multiply(parValue).divide(price, 4, RoundingMode.HALF_UP);
		}
		return result;
	}

	@Override
	public BigDecimal calculatePeRatio(final Stock stock, final BigDecimal price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculateGeometricMean(final List<BigDecimal> prices) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculateVolumeWeightedStockPrice(final List<BigDecimal> tradedPrices, final List<Integer> qty) {
		// TODO Auto-generated method stub
		return null;
	}

}
