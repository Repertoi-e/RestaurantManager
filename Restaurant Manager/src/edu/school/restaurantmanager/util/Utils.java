package edu.school.restaurantmanager.util;

// Полезни функции, използвани често, без място другаде.

import java.awt.*;

public class Utils {
	
	// Изчислява процент от цяло число.
	// Извършва закръглянето и cast-ването
	public static int percent(int n, int percent) {
		return (int) Math.round((double) n * percent / 100);
	}

	public static String getPriceAsString(int price) {
		return "" + price / 100 + "." + price % 100 + " лв.";
	}

	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);

		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

		g.setFont(font);
		g.drawString(text, x, y);
	}
}
