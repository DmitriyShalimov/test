package com.minexsystems;
/**
 * Provides a way to override the system time.
 * Useful for unit-testing purposes.
 */
public class Clock   { //TODO use this class in CacheMap implementation to work with date/time
	private static Long time;
	
	/**
	 * Returns the system time if no time has been explicitly
	 * set using setTime(...)
	 */
	public static long getTime() {
		if (time == null) {
			return System.currentTimeMillis();
		} else {
			return time;
		}
	}
	
	/**
	 * Sets the time. This will cause getTime() to return the given time
	 * instead of the system time.
	 */
	public static void setTime(long time) {
		Clock.time = time;
	}
	
	/**
	 * Clears the time. This will cause getTime() to return the system time.
	 */
	public static void clearTime() {
		Clock.time = null;
	}
}
