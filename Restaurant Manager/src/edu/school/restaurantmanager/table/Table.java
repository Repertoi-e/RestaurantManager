package edu.school.restaurantmanager.table;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.LocalTime;

import javax.swing.JButton;

import edu.school.restaurantmanager.util.Utils;

// Всяка маса е бутон с различен изглед.

public class Table extends JButton {

	public TableUnavailableInfo UnavailableInfo = null;

	TableColorPalette m_Palette;
	int m_TableWidth, m_TableHeight;
	int m_RoundnessX, m_RoundnessY;
	int m_PaddingX, m_PaddingY;
	TableChairComposition m_ChairComposition;
	TableStatus m_StatusDisplay = new TableStatus();
	
	/*
	 * x, y - мястото на масата в полето
	 * width, height - големината на масата
	 */
	public Table(int x, int y, int width, int height, TableColorPalette color) {
		this.setBounds(x, y, width, height);
        m_StatusDisplay.updateBounds(width / 2, height / 2);
		
		m_Palette = color;
		
		// Разстоянието от страните на бутона и масата
		m_PaddingX = Utils.percent(width, 15);
		m_PaddingY = Utils.percent(height, 15);

		m_TableWidth = width - m_PaddingX * 2;
		m_TableHeight = height - m_PaddingY * 2;

		// Закръглеността на ъглищата
		m_RoundnessX = Utils.percent(width, 12);
		m_RoundnessY = Utils.percent(height, 12);

		m_ChairComposition = new TableChairComposition(this);

		this.addActionListener((e) -> {
		    if (UnavailableInfo == null)
		        makeUnavailable();
		    else
		        makeAvailable();
        });
	}

	public void makeUnavailable() {
        UnavailableInfo = new TableUnavailableInfo();
        UnavailableInfo.Hour = LocalTime.now().getHour();
        UnavailableInfo.Minute = LocalTime.now().getMinute();
        UnavailableInfo.Order = new TableOrder();
    }

    public void makeAvailable() {
        UnavailableInfo = null;
    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(TableView.BACKGROUND_COLOR);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.translate(this.getWidth() / 2, this.getHeight() / 2);
		m_ChairComposition.draw((Graphics2D) g);
		g.translate(-this.getWidth() / 2, -this.getHeight() / 2);
			
		g.setColor(m_Palette.Top);
		// Java Bug: Само с fill масата не е закръглена, но с fill + draw е. 
		g.fillRoundRect(m_PaddingX, m_PaddingY, m_TableWidth, m_TableHeight, m_RoundnessX, m_RoundnessY);
		g.drawRoundRect(m_PaddingX, m_PaddingY, m_TableWidth, m_TableHeight, m_RoundnessX, m_RoundnessY);

        m_StatusDisplay.draw((Graphics2D) g, this);
	}
}
