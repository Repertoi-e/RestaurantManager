package edu.school.restaurantmanager.table;

import java.awt.*;

public abstract class ChairComposition {

    // Размерът на един стол в px
    protected static final int CHAIR_SIZE = 30;

    // Минималното разстояние между два стола в px
    protected static final int CHAIR_MARGIN = 6;

    // Мястото, което заема един стол
    protected static final int CHAIR_SPACE = CHAIR_SIZE + CHAIR_MARGIN;

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

    protected void drawChair(TableColorPalette palette, Graphics2D g2d) {
        // Основна част
        g2d.setColor(palette.Chair);
        g2d.fillPolygon(CHAIR_POLYGON);

        // Сянка
        g2d.translate(2, 0);
        {
            g2d.setColor(palette.ChairShadow);
            g2d.fillPolygon(CHAIR_POLYGON);
        }
        g2d.translate(-2, 0);
    }

    // Рисува спрямо центъра на масата
    public abstract void draw(Dimension tableDimension, TableColorPalette palette, Graphics2D g2d);
}
