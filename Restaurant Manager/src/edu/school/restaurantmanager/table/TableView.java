package edu.school.restaurantmanager.table;

import java.awt.*;

import javax.swing.JPanel;

// Полето, където се показват масите.

@SuppressWarnings("serial")
public class TableView extends JPanel {

	public static final Color BACKGROUND_COLOR = Color.decode("#455a64");
	
	public TableView() {
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(null);
		
		// Пример за жълта маса
		TableColorPalette yellow = new TableColorPalette();
		{
			yellow.Top = Color.decode("#fdd835");
			yellow.Chair = Color.decode("#fbc02d");
			yellow.ChairShadow = Color.decode("#e8b228");
		}
		
		this.add(new Table(100, 100, 180, 250, yellow));
		this.add(new Table(300, 350, 90, 90, yellow));
	}
}
