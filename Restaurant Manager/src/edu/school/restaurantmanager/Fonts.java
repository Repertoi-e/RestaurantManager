package edu.school.restaurantmanager;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class Fonts {
	public static final void registerFonts() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("SourceSansPro-Regular.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("SourceSansPro-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("SourceSansPro-Italic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("SourceSansPro-BoldItalic.ttf")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
