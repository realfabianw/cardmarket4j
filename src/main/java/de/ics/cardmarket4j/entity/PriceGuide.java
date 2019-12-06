package de.ics.cardmarket4j.entity;

import java.math.BigDecimal;

/**
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Product
 * @author QUE
 *
 */
public class PriceGuide {
	private BigDecimal sell;
	private BigDecimal low;
	private BigDecimal lowExPlus;
	private BigDecimal lowFoil;
	private BigDecimal avg;
	private BigDecimal trend;

	public PriceGuide(BigDecimal sell, BigDecimal low, BigDecimal lowExPlus, BigDecimal lowFoil, BigDecimal avg,
			BigDecimal trend) {
		this.sell = sell;
		this.low = low;
		this.lowExPlus = lowExPlus;
		this.lowFoil = lowFoil;
		this.avg = avg;
		this.trend = trend;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceGuide other = (PriceGuide) obj;
		if (avg == null) {
			if (other.avg != null)
				return false;
		} else if (!avg.equals(other.avg))
			return false;
		if (low == null) {
			if (other.low != null)
				return false;
		} else if (!low.equals(other.low))
			return false;
		if (lowExPlus == null) {
			if (other.lowExPlus != null)
				return false;
		} else if (!lowExPlus.equals(other.lowExPlus))
			return false;
		if (lowFoil == null) {
			if (other.lowFoil != null)
				return false;
		} else if (!lowFoil.equals(other.lowFoil))
			return false;
		if (sell == null) {
			if (other.sell != null)
				return false;
		} else if (!sell.equals(other.sell))
			return false;
		if (trend == null) {
			if (other.trend != null)
				return false;
		} else if (!trend.equals(other.trend))
			return false;
		return true;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public BigDecimal getLow() {
		return low;
	}

	public BigDecimal getLowExPlus() {
		return lowExPlus;
	}

	public BigDecimal getLowFoil() {
		return lowFoil;
	}

	public BigDecimal getSell() {
		return sell;
	}

	public BigDecimal getTrend() {
		return trend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avg == null) ? 0 : avg.hashCode());
		result = prime * result + ((low == null) ? 0 : low.hashCode());
		result = prime * result + ((lowExPlus == null) ? 0 : lowExPlus.hashCode());
		result = prime * result + ((lowFoil == null) ? 0 : lowFoil.hashCode());
		result = prime * result + ((sell == null) ? 0 : sell.hashCode());
		result = prime * result + ((trend == null) ? 0 : trend.hashCode());
		return result;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public void setLowExPlus(BigDecimal lowExPlus) {
		this.lowExPlus = lowExPlus;
	}

	public void setLowFoil(BigDecimal lowFoil) {
		this.lowFoil = lowFoil;
	}

	public void setSell(BigDecimal sell) {
		this.sell = sell;
	}

	public void setTrend(BigDecimal trend) {
		this.trend = trend;
	}

	@Override
	public String toString() {
		return "PriceGuide [" + (sell != null ? "sell=" + sell + ", " : "") + (low != null ? "low=" + low + ", " : "")
				+ (lowExPlus != null ? "lowExPlus=" + lowExPlus + ", " : "")
				+ (lowFoil != null ? "lowFoil=" + lowFoil + ", " : "") + (avg != null ? "avg=" + avg + ", " : "")
				+ (trend != null ? "trend=" + trend : "") + "]";
	}
}
