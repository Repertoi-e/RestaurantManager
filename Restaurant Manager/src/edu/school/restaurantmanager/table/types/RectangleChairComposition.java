package edu.school.restaurantmanager.table.types;

import edu.school.restaurantmanager.table.ChairComposition;
import edu.school.restaurantmanager.table.TableColorPalette;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RectangleChairComposition extends ChairComposition {

    private void drawChairGroup(int tables, int xOffset, int yOffset, int space, TableColorPalette palette, Graphics2D g2d) {
        for (int i = 0; i < tables; i++)
        {
            AffineTransform old = g2d.getTransform();
            g2d.translate(xOffset, yOffset + i * space);
            this.drawChair(palette, g2d);
            g2d.setTransform(old);
        }
    }

    // Рисува спрямо центъра на масата
    @Override
    public void draw(Dimension tableDimension, TableColorPalette palette, Graphics2D g2d) {
        int chairsX = tableDimension.width / CHAIR_SPACE;
        int chairsY = tableDimension.height / CHAIR_SPACE;
        int chairXMargin = CHAIR_MARGIN + (int) ((double) (tableDimension.width - chairsX * CHAIR_SPACE) / chairsX);
        int chairYMargin = CHAIR_MARGIN + (int) ((double) (tableDimension.height - chairsY * CHAIR_SPACE) / chairsY);

        // Рисува столове по по-дългите страни на масата,
        // ако е квадрат - на всички.
        if (tableDimension.width > tableDimension.height)
            chairsY = 0;
        else if (tableDimension.width < tableDimension.height)
            chairsX = 0;

        int topLeftX = -tableDimension.width / 2;
        int topLeftY = -tableDimension.height / 2;
        int xSpace = chairXMargin + CHAIR_SIZE;
        int ySpace = chairYMargin + CHAIR_SIZE;

        int xOffset = topLeftX;
        int yOffset = topLeftY + ySpace / 2;
        {
            drawChairGroup(chairsY, xOffset, yOffset, ySpace, palette, g2d);
            g2d.rotate(Math.toRadians(180));
            drawChairGroup(chairsY, xOffset, yOffset, ySpace, palette, g2d);
            g2d.rotate(-Math.toRadians(180));
        }

        xOffset = topLeftY;
        yOffset = topLeftX + xSpace / 2;
        g2d.rotate(Math.toRadians(90));
        {
            drawChairGroup(chairsX, xOffset, yOffset, xSpace, palette, g2d);
            g2d.rotate(Math.toRadians(180));
            drawChairGroup(chairsX, xOffset, yOffset, xSpace, palette, g2d);
            g2d.rotate(Math.toRadians(-180));
        }
        g2d.rotate(-Math.toRadians(90));
    }
}
