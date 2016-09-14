package com.vvirlan.ss.model;

public final class Stock {
	private final String symbol;
	private final StockType type;
	private final Long lastDividend;
	private final double fixedDividend;
	private final Long parValue;

	public Stock(final String symbol, final StockType type, final Long lastDividend, final double fixedDividend,
			final Long parValue) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public String getSymbol() {
		return symbol;
	}

	public StockType getType() {
		return type;
	}

	public Long getLastDividend() {
		return lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public Long getParValue() {
		return parValue;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", type=" + type + ", lastDividend=" + lastDividend + ", fixedDividend="
				+ fixedDividend + ", parValue=" + parValue + "]";
	}

}
