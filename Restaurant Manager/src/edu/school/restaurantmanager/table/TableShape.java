package edu.school.restaurantmanager.table;

import java.awt.*;

public abstract class TableShape {

    protected ChairComposition m_ChairComposition;

    public abstract void draw(Dimension tableDimension, TableColorPalette palette, Graphics2D g2d);

    public abstract Dimension getMinimumSize();
    public abstract Dimension getMaximumSize();
    public abstract boolean shouldAlwaysBeSquare();
}
