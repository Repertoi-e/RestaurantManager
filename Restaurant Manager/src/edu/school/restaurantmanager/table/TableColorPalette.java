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
        UNAVAILABLE.Top = new Color(56, 141, 133);
        UNAVAILABLE.Chair = new Color(56, 111, 120);
        UNAVAILABLE.ChairShadow = new Color(56, 116, 107);
    }
}
