package edu.school.restaurantmanager.menu;

import java.awt.Color;

import javax.swing.JPanel;

// Полето, където се показва менюто.

@SuppressWarnings("serial")
public class MenuView extends JPanel {

	public static final Color BACKGROUND_COLOR = Color.decode("#718792");
	
	public MenuView() {
		this.setBackground(BACKGROUND_COLOR);
	}
}
