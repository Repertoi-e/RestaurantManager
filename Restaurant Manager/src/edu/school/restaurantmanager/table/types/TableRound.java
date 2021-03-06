package edu.school.restaurantmanager.table.types;

import edu.school.restaurantmanager.table.TableColorPalette;
import edu.school.restaurantmanager.table.TableShape;

import java.awt.*;

public class TableRound extends TableShape {

    public TableRound() {
        m_ChairComposition = new RoundChairComposition();
    }

    @Override
    public void draw(Dimension tableDimension, TableColorPalette palette, Graphics2D g2d) {
        g2d.translate(tableDimension.width / 2, tableDimension.height / 2);
        m_ChairComposition.draw(tableDimension, palette, g2d);
        g2d.translate(-tableDimension.width / 2, -tableDimension.height / 2);

        g2d.setColor(palette.Top);
        g2d.fillOval(0, 0, tableDimension.width, tableDimension.height);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(90, 90);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(250, 250);
    }

    @Override
    public boolean shouldAlwaysBeSquare()
    {
        return true;
    }

    @Override
    public String toString() {
        return "Round";
    }
}
