package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;

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
