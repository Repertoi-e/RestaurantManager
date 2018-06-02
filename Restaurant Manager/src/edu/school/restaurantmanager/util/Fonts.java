package edu.school.restaurantmanager.util;

import edu.school.restaurantmanager.MainFrame;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class Fonts {
	public static void registerFonts() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream("SourceSansPro-Regular.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream("SourceSansPro-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream("SourceSansPro-Italic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream("SourceSansPro-BoldItalic.ttf")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
