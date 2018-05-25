package edu.school.restaurantmanager.util;

// Полезни функции, използвани често, без място другаде.

public class Utils {
	
	// Изчислява процент от цяло число.
	// Извършва закръглянето и cast-ването
	public static final int percent(int n, int percent) {
		return (int) Math.round((double) n * percent / 100);
	}
}
