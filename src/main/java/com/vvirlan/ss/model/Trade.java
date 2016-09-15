/**
 *
 */
package com.vvirlan.ss.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Trade entity
 *
 * @author A
 *
 */
public class Trade {
	private final Date timestamp;
	private final long qty;
	private final BigDecimal price;
	private final TradeType tradeType;

	public Trade(final Date timestamp, final long qty, final BigDecimal price, final TradeType tradeType) {
		super();

		if (timestamp == null) {
			throw new IllegalArgumentException("Timestamp cannot be null!");
		}

		if (price == null) {
			throw new IllegalArgumentException("Price cannot be null!");
		}

		if (tradeType == null) {
			throw new IllegalArgumentException("Trade Type cannot be null!");
		}

		this.timestamp = timestamp;
		this.qty = qty;
		this.price = price;
		this.tradeType = tradeType;
	}

	public Date getTimestamp() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + price.hashCode();
		result = prime * result + (int) (qty ^ qty >>> 32);
		result = prime * result + timestamp.hashCode();
		result = prime * result + tradeType.hashCode();
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
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (tradeType != other.tradeType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Trade [timestamp=" + timestamp + ", qty=" + qty + ", price=" + price + ", tradeType=" + tradeType + "]";
	}

}
