package edu.school.restaurantmanager;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class Fonts {
	public static final void RegisterFonts() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("SourceSansPro-Regular.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("SourceSansPro-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("SourceSansPro-Italic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("SourceSansPro-BoldItalic.ttf")));
		} catch (Exception e) {}
	}
}
