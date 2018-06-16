package edu.school.restaurantmanager.table;

import java.awt.*;
import java.time.LocalTime;

import javax.swing.JButton;

import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.util.Utils;

// Всяка маса е бутон с различен изглед.

public class Table extends JButton {

    TableOrder Order = null;

	TableColorPalette m_Palette;
	int m_TableWidth, m_TableHeight;
	int m_RoundnessX, m_RoundnessY;
	private int m_PaddingX, m_PaddingY;
	private TableChairComposition m_ChairComposition;
	private TableGraphics m_Graphics = new TableGraphics();
	
	/*
	 * x, y - мястото на масата в полето
	 * width, height - големината на масата
	 */
	Table(int x, int y, int width, int height, TableColorPalette color) {
		this.setBounds(x, y, width, height);
		this.setContentAreaFilled(false);

		m_Palette = color;
		m_ChairComposition = new TableChairComposition(this);

        updateBounds();

        this.addActionListener((e) -> {
		    if (!MainFrame.getTableView().isEditing()) {
                if (Order == null)
                    makeUnavailable();
                else
                    makeAvailable();
            }
        });
	}

	void updateBounds() {
	    int width = this.getWidth();
	    int height = this.getHeight();

        m_Graphics.updateBounds(width / 2, height / 2);

        // Разстоянието от страните на бутона и масата
        m_PaddingX = Utils.percent(width, 15);
        m_PaddingY = Utils.percent(height, 15);

        m_TableWidth = width - m_PaddingX * 2;
        m_TableHeight = height - m_PaddingY * 2;

        // Закръглеността на ъглищата
        m_RoundnessX = Utils.percent(width, 12);
        m_RoundnessY = Utils.percent(height, 12);

        m_ChairComposition.recalculate();
    }

    private void makeUnavailable() {
        Order = new TableOrder();
        Order.Hour = LocalTime.now().getHour();
        Order.Minute = LocalTime.now().getMinute();
    }

    private void makeAvailable() {
        Order = null;
    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.translate(this.getWidth() / 2, this.getHeight() / 2);
		m_ChairComposition.draw((Graphics2D) g);
		g.translate(-this.getWidth() / 2, -this.getHeight() / 2);
			
		g.setColor(m_Palette.Top);
		// Java Bug: Само с fill масата не е закръглена, но с fill + draw е. 
		g.fillRoundRect(m_PaddingX, m_PaddingY, m_TableWidth, m_TableHeight, m_RoundnessX, m_RoundnessY);
		g.drawRoundRect(m_PaddingX, m_PaddingY, m_TableWidth, m_TableHeight, m_RoundnessX, m_RoundnessY);

        m_Graphics.draw((Graphics2D) g, this);
	}
}
