package edu.school.restaurantmanager.table.types;

import edu.school.restaurantmanager.table.ChairComposition;
import edu.school.restaurantmanager.table.TableColorPalette;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RoundChairComposition extends ChairComposition {

    // Рисува спрямо центъра на масата
    @Override
    public void draw(Dimension tableDimension, TableColorPalette palette, Graphics2D g2d) {
        int radius = tableDimension.width / 2;

        // При малка маса слагаме само един стол
        int chairs = 1;
        double angle = 0;
        if (radius > 30) {
            // Смята колко стола могат да се подредят
            // по обиколката на кръглата маса.
            double circumference = Math.PI * radius * 2;
            chairs = (int) circumference / (CHAIR_SPACE * 2);
            angle = Math.PI * 2 / chairs;
        }

        if (chairs < 5)
            radius -= 2; // столoвете излизат

        AffineTransform old = g2d.getTransform();
        for (int i = 0; i < chairs; i++)
        {
            g2d.translate(0, -radius);
            g2d.rotate(Math.toRadians(90)); // завърта столовете
            drawChair(palette, g2d);
            g2d.rotate(-Math.toRadians(90));
            g2d.translate(0, radius);
            g2d.rotate(angle);
        }
        g2d.setTransform(old);
    }
}
