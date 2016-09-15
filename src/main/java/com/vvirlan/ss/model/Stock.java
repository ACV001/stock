package com.vvirlan.ss.model;

import java.math.BigDecimal;

/**
 * The entity class representing a Stock
 *
 * @author A
 *
 */
public final class Stock {
	private final String symbol;
	private final StockType type;
	private final long lastDividend;
	private final BigDecimal fixedDividend;
	private final long parValue;

	public Stock(final String symbol, final StockType type, final long lastDividend, final BigDecimal fixedDividend,
			final long parValue) {
		super();
		// Validations
		if (symbol == null || symbol.isEmpty()) {
			throw new IllegalArgumentException("Symbol cannot be null!");
		}

		if (type == null) {
			throw new IllegalArgumentException("Stock type cannot be null!");
		}

		if (fixedDividend == null) {
			throw new IllegalArgumentException("Fixed dividend cannot be null!");
		}

		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	/**
	 * Gets the symbol
	 *
	 * @return
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Gets the type
	 *
	 * @return
	 */
	public StockType getType() {
		return type;
	}

	/**
	 * Gets Last dividend
	 *
	 * @return
	 */
	public long getLastDividend() {
		return lastDividend;
	}

	/**
	 * Gets the fixed dividend
	 *
	 * @return
	 */
	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * Gets par value
	 *
	 * @return
	 */
	public long getParValue() {
		return parValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fixedDividend.hashCode();
		result = prime * result + (int) (lastDividend ^ lastDividend >>> 32);
		result = prime * result + (int) (parValue ^ parValue >>> 32);
		result = prime * result + symbol.hashCode();
		result = prime * result + type.hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Stock)) {
			return false;
		}
		final Stock other = (Stock) obj;
		if (fixedDividend == null) {
			if (other.fixedDividend != null) {
				return false;
			}
		} else if (!fixedDividend.equals(other.fixedDividend)) {
			return false;
		}
		if (lastDividend != other.lastDividend) {
			return false;
		}
		if (parValue != other.parValue) {
			return false;
		}
		if (symbol == null) {
			if (other.symbol != null) {
				return false;
			}
		} else if (!symbol.equals(other.symbol)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", type=" + type + ", lastDividend=" + lastDividend + ", fixedDividend="
				+ fixedDividend + ", parValue=" + parValue + "]";
	}

}
