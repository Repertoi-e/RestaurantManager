package edu.school.restaurantmanager.table.types;

import edu.school.restaurantmanager.table.TableColorPalette;

import java.awt.*;

public class TableDiamond extends TableRectangle {

    @Override
    public void draw(Dimension tableDimension, TableColorPalette palette, Graphics2D g2d) {
        g2d.translate(tableDimension.width / 2, tableDimension.height / 2);
        g2d.rotate(Math.toRadians(45));

        m_ChairComposition.draw(tableDimension, palette, g2d);
        g2d.setColor(palette.Top);

        g2d.fillRect(-tableDimension.width / 2, -tableDimension.height / 2, tableDimension.width, tableDimension.height);

        g2d.rotate(-Math.toRadians(45));
        g2d.translate(-tableDimension.width / 2, -tableDimension.height / 2);
    }

    @Override
    public boolean shouldAlwaysBeSquare()
    {
        return true;
    }
}
