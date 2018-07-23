package com.example.util;

/**
 * @author Ravisankar C
 *
 */
public class FlightRange {
	private int lowerLimit;
	private int upperLimit;
	public FlightRange(int lowerLimit, int upperLimit) {
		super();
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
	}
	public int getLowerLimit() {
		return lowerLimit;
	}
	public int getUpperLimit() {
		return upperLimit;
	}
	
}
