package edu.school.restaurantmanager.util;

// Полезни функции, използвани често, без място другаде.

import edu.school.restaurantmanager.table.Table;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Utils {
	
	// Изчислява процент от цяло число.
	// Извършва закръглянето и cast-ването
	public static int percent(int n, int percent) {
		return (int) Math.round((double) n * percent / 100);
	}

	public static String getPriceAsString(int price) {
		return getPriceAsString(price, true);
	}

    public static String getPriceAsString(int price, boolean currency) {
        return price / 100 + "." + String.format("%02d", price % 100) + (currency ? " лв." : "");
    }

	public static ImageIcon getScaledButtonIcon(URL input, int size) {
        BufferedImage editIcon = null;
        try
        {
            editIcon = ImageIO.read(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (editIcon != null)
            return new ImageIcon(editIcon.getScaledInstance(size, -1, Image.SCALE_SMOOTH));
        return null;
    }

	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);

		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

		g.setFont(font);
		g.drawString(text, x, y);
	}
}
