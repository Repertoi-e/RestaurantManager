package edu.school.restaurantmanager.table;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import edu.school.restaurantmanager.util.Pair;

// Поставя столовете около масата

class TableChairComposition {

	// Размерът на един стол в px
	private static final int CHAIR_SIZE = 30;

	// Минималното разстояние между два стола в px
    private static final int CHAIR_MARGIN = 6;

	// Мястото, което заема един стол
    private static final int CHAIR_SPACE = CHAIR_SIZE + CHAIR_MARGIN;

    private static final Polygon CHAIR_POLYGON;
	static {
		// Горната част на стола е по-малка.
		int chairTopWidth = (int) Math.round((double) CHAIR_SIZE * 8 / 9);
	
		CHAIR_POLYGON = new Polygon(new int[] {
			0, -4, -4, 0 
		}, 
		new int[] {
			-CHAIR_SIZE / 2, -chairTopWidth / 2, chairTopWidth / 2, CHAIR_SIZE / 2 
		}, 4);
	}

    private Table m_Parent;
    private Pair<Integer, Integer> m_X, m_Y;
	
	TableChairComposition(Table parent) {
		m_Parent = parent;

        recalculate();
	}

	void recalculate() {
        // Смята колко стола могат да се съберат и какво ще бъде разстоянието между тях.
        m_X = calculateTablesAndMargin(m_Parent.m_TableWidth - m_Parent.m_RoundnessX * 2);
        m_Y = calculateTablesAndMargin(m_Parent.m_TableHeight - m_Parent.m_RoundnessY * 2);
    }

    private Pair<Integer, Integer> calculateTablesAndMargin(int remain) {
		int tables = remain / CHAIR_SPACE;
		int leftForPadding = remain - tables * CHAIR_SPACE;
		
		return new Pair<>(tables, CHAIR_MARGIN + (int) ((double) leftForPadding / tables));
	}

    private void drawChair(Graphics2D g2d) {
		// Основна част
		g2d.setColor(m_Parent.m_Palette.Chair);
		g2d.fillPolygon(CHAIR_POLYGON);

		// Сянка
		g2d.translate(2, 0);
		{
			g2d.setColor(m_Parent.m_Palette.ChairShadow);
			g2d.fillPolygon(CHAIR_POLYGON);
		}
		g2d.translate(-2, 0);
	}

    private void drawChairGroup(Graphics2D g2d, boolean horizontal) {
		int topLeftX = -m_Parent.m_TableWidth / 2;
		int topLeftY = -m_Parent.m_TableHeight / 2;

		int space = m_Y.Second + CHAIR_SIZE;
		int xOffset = topLeftX; 
		int yOffset = topLeftY + m_Parent.m_RoundnessY + space / 2;

		if (horizontal) {
			space = m_X.Second + CHAIR_SIZE;
			xOffset = topLeftY;
			yOffset = topLeftX + m_Parent.m_RoundnessX + space / 2;
		}

		int tables = horizontal ? m_X.First : m_Y.First;
		for (int i = 0; i < tables; i++) {
			AffineTransform old = g2d.getTransform();
			g2d.translate(xOffset, yOffset + i * space);
			drawChair(g2d);
			g2d.setTransform(old);
		}
	}
	
	void draw(Graphics2D g2d) {
		for (int i = 1; i < 5; i++) {
			drawChairGroup(g2d, i % 2 == 0);
			g2d.rotate(Math.toRadians(90));
		}
	}
}
