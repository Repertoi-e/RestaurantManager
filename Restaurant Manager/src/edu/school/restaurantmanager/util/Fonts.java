package edu.school.restaurantmanager.util;

import edu.school.restaurantmanager.MainFrame;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Fonts {

    public static Font createFont(String resource) throws Exception {
        Font font = Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream(resource));

        Map<TextAttribute, Object> attribs = new HashMap<>();
        attribs.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
        return font.deriveFont(attribs);
    }

	public static void registerFonts() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(createFont("SourceSansPro-Regular.ttf"));
            ge.registerFont(createFont("SourceSansPro-Bold.ttf"));
            ge.registerFont(createFont("SourceSansPro-Italic.ttf"));
            ge.registerFont(createFont("SourceSansPro-BoldItalic.ttf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
