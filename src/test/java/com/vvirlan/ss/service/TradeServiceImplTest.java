package com.vvirlan.ss.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.vvirlan.ss.TestUtils;
import com.vvirlan.ss.model.Stock;
import com.vvirlan.ss.model.StockType;
import com.vvirlan.ss.model.Trade;
import com.vvirlan.ss.model.TradeType;
import com.vvirlan.ss.repository.InMemoryStockRepository;
import com.vvirlan.ss.repository.InMemoryTradeRepository;
import com.vvirlan.ss.repository.StockRepository;
import com.vvirlan.ss.repository.TradeRepository;

@RunWith(EasyMockRunner.class)

public class TradeServiceImplTest extends EasyMockSupport {

	TradeService tradeService;
	StockService stockService;

	TradeRepository tradeRepository;

	@Before
	public void setUp() {
		tradeRepository = new InMemoryTradeRepository();
		final StockRepository stockRepository = new InMemoryStockRepository();

		stockService = new StockServiceImpl(stockRepository);
		tradeService = new TradeServiceImpl(tradeRepository);
		TestUtils.createStocks(stockService);
	}

	@Test
	public void findTradesBySymbolNeg() {
		final long qty = 10;
		final BigDecimal price = new BigDecimal("10.50");
		final TradeType tradeType = TradeType.BUY;
		final Date now = new Date();
		final Stock s = new Stock("LOC", StockType.COMMON, 5L, new BigDecimal("0.3"), 4L);
		final Trade t = new Trade(s, now.getTime() + TestUtils.getMinutesAsMs(4), qty, price, tradeType);
		tradeService.recordTrade(t);
		final List<Trade> res = tradeService.findTradeBySymbol("ALE");
		assertEquals(0, res.size());
	}

	@Test
	public void findTradesBySymbolPos() {
		final long qty = 10;
		final BigDecimal price = new BigDecimal("10.50");
		final TradeType tradeType = TradeType.BUY;
		final Date now = new Date();
		final Stock s = new Stock("LOC", StockType.COMMON, 5L, new BigDecimal("0.3"), 4L);
		final Trade t = new Trade(s, now.getTime() + TestUtils.getMinutesAsMs(4), qty, price, tradeType);
		tradeService.recordTrade(t);
		final List<Trade> res = tradeService.findTradeBySymbol("LOC");
		assertEquals(1, res.size());
		assertEquals(t, res.get(0));
	}

	@Test
	public void findTradesBySymbol() {
		final List<Trade> empty = tradeService.findTradeBySymbol("TEA");
		assertNotNull(empty);
		assertTrue(empty.isEmpty());
	}

	@Test
	public void findTradesInPastMinutesPos3() {
		final Date now = new Date();
		final long qty = 10;
		final BigDecimal price = new BigDecimal("10.50");
		final TradeType tradeType = TradeType.BUY;
		final Stock s = new Stock("LOC", StockType.COMMON, 5L, new BigDecimal("0.3"), 4L);
		Trade t = new Trade(s, now.getTime() + TestUtils.getMinutesAsMs(4), qty, price, tradeType);
		tradeService.recordTrade(t);
		t = new Trade(s, now.getTime(), qty, price, tradeType);
		tradeService.recordTrade(t);
		t = new Trade(s, now.getTime() - TestUtils.getMinutesAsMs(10), qty, price, tradeType);
		tradeService.recordTrade(t);

		final Collection<Trade> pastTrades = tradeService.findTradesInPastMinutes(5);
		assertNotNull(pastTrades);
		assertTrue(pastTrades.size() == 1);
	}

	@Test
	public void findTradesInPastMinutesPos2() {
		final Date now = new Date();
		final long qty = 10;
		final BigDecimal price = new BigDecimal("10.50");
		final TradeType tradeType = TradeType.BUY;
		final Stock s = new Stock("LOC", StockType.COMMON, 5L, new BigDecimal("0.3"), 4L);
		Trade t = new Trade(s, now.getTime() - TestUtils.getMinutesAsMs(4), qty, price, tradeType);
		tradeService.recordTrade(t);
		t = new Trade(s, now.getTime(), qty, price, tradeType);
		tradeService.recordTrade(t);
		t = new Trade(s, now.getTime() - TestUtils.getMinutesAsMs(10), qty, price, tradeType);
		tradeService.recordTrade(t);

		final Collection<Trade> pastTrades = tradeService.findTradesInPastMinutes(5);
		assertNotNull(pastTrades);
		assertTrue(pastTrades.size() == 2);
	}

	@Test
	public void findTradesInPastMinutesPos1() {
		final Date now = new Date();
		final long qty = 10;
		final BigDecimal price = new BigDecimal("10.50");
		final TradeType tradeType = TradeType.BUY;
		final Stock s = new Stock("LOC", StockType.COMMON, 5L, new BigDecimal("0.3"), 4L);
		final Trade t = new Trade(s, now.getTime(), qty, price, tradeType);
		tradeService.recordTrade(t);
		final Collection<Trade> pastTrades = tradeService.findTradesInPastMinutes(5);
		assertNotNull(pastTrades);
		assertTrue(pastTrades.size() == 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findTradesInPastMinutes0Neg() {
		tradeService.findTradesInPastMinutes(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findTradesInPastMinutesNeg() {
		tradeService.findTradesInPastMinutes(-1);
	}

	@Test
	public void recordTradePosTest() {
		final Date now = new Date();
		final long qty = 10;
		final BigDecimal price = new BigDecimal("10.50");
		final TradeType tradeType = TradeType.BUY;
		final Stock s = new Stock("LOC", StockType.COMMON, 5L, new BigDecimal("0.3"), 4L);
		final Trade t = new Trade(s, now.getTime(), qty, price, tradeType);
		tradeService.recordTrade(t);
		final Collection<Trade> allTrades = tradeService.getAllTrades();
		assertNotNull(allTrades);
		assertTrue(allTrades.size() == 1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void recordTradeNegTest() {
		tradeService.recordTrade(null);
	}

}
