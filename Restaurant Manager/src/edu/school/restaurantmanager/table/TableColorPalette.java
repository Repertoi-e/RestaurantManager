package edu.school.restaurantmanager.table;

import java.awt.Color;

// Цветовете, използвани при рисуването на масa

public class TableColorPalette
{
	// Масата
	public Color Top;
	
	// Столът
	Color Chair;
	
	// Сянката от масата на стола
	Color ChairShadow;

	// Цветовете, когато масите за свободни и заети
    static final TableColorPalette AVAILABLE = new TableColorPalette();
    static {
        AVAILABLE.Top = new Color(129, 133, 143);
        AVAILABLE.Chair = new Color(113, 118, 124);
        AVAILABLE.ChairShadow = new Color(104, 109, 116);
    }

    static final TableColorPalette UNAVAILABLE = new TableColorPalette();
    static {
        UNAVAILABLE.Top = Color.decode("#fdd835");
        UNAVAILABLE.Chair = Color.decode("#fbc02d");
        UNAVAILABLE.ChairShadow = Color.decode("#e8b228");
    }
}
