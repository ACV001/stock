/**
 *
 */
package com.vvirlan.ss.model;

import java.math.BigDecimal;

/**
 * The Trade entity
 *
 * @author A
 *
 */
public class Trade {
	private final Stock stock;
	private final long timestamp;
	private final long qty;
	private final BigDecimal price;
	private final TradeType tradeType;

	public Trade(final Stock stock, final long timestamp, final long qty, final BigDecimal price,
			final TradeType tradeType) {
		super();

		if (stock == null) {
			throw new IllegalArgumentException("Stock cannot be null!");
		}

		if (timestamp <= 0L) {
			throw new IllegalArgumentException("Timestamp cannot be negative or zero!");
		}

		if (price == null) {
			throw new IllegalArgumentException("Price cannot be null!");
		}

		if (tradeType == null) {
			throw new IllegalArgumentException("Trade Type cannot be null!");
		}

		this.stock = stock;
		this.timestamp = timestamp;
		this.qty = qty;
		this.price = price;
		this.tradeType = tradeType;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getQty() {
		return qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
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
		result = prime * result + (price == null ? 0 : price.hashCode());
		result = prime * result + (int) (qty ^ qty >>> 32);
		result = prime * result + (stock == null ? 0 : stock.hashCode());
		result = prime * result + (int) (timestamp ^ timestamp >>> 32);
		result = prime * result + (tradeType == null ? 0 : tradeType.hashCode());
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
		if (!(obj instanceof Trade)) {
			return false;
		}
		final Trade other = (Trade) obj;
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (qty != other.qty) {
			return false;
		}
		if (stock == null) {
			if (other.stock != null) {
				return false;
			}
		} else if (!stock.equals(other.stock)) {
			return false;
		}
		if (timestamp != other.timestamp) {
			return false;
		}
		if (tradeType != other.tradeType) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trade [stock=" + stock + ", timestamp=" + timestamp + ", qty=" + qty + ", price=" + price
				+ ", tradeType=" + tradeType + "]";
	}

}
