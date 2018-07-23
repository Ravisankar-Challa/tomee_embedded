package com.example.util;

/**
 * @author Ravisankar C
 *
 */
public class Cache {
	public static final Store store = new Store();
	
	private Cache() {
		throw new UnsupportedOperationException("You are not allowed to instantiate "+this.getClass().getName()+" class");
	}
}
