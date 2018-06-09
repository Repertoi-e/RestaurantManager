package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.ui.UILabel;
import edu.school.restaurantmanager.util.Utils;

import java.awt.*;
import java.awt.geom.Point2D;

class MenuItemGraphics {

    private MenuItem m_Parent;
    private UILabel m_Name, m_Price;
    private RadialGradientPaint m_RadialPaint;

    MenuItemGraphics(MenuItem parent) {
        m_Parent = parent;

        m_Name = new UILabel(new Font("SourceSansPro", Font.BOLD, 100), m_Parent.getName(), 1.9f);
        m_Name.setForeground(Color.decode("#eeeeee"));

        m_Price = new UILabel(new Font("SourceSansPro", Font.BOLD, 100), 1.3f);
        m_Price.setForeground(Color.decode("#eeeeee"));
    }

    void updateBounds(int halfWidth, int halfHeight) {
        int nameWidth = Utils.percent(halfWidth * 2, 80) / 2;
        int nameHeight = Utils.percent(halfHeight * 2, 20) / 2;

        m_Name.setBounds(halfWidth - nameWidth,
                         halfHeight - nameHeight,
                      nameWidth * 2,
                     nameHeight * 2);

        int priceWidth = Utils.percent(halfWidth * 2, 50) / 2;
        int priceHeight = Utils.percent(halfHeight * 2, 15) / 2;
        m_Price.setBounds(halfWidth - priceWidth,
                         halfHeight - priceHeight + nameHeight * 2,
                     priceWidth * 2,
                     priceHeight * 2);

        Point2D.Float center = new Point2D.Float(halfWidth, halfHeight);
        Color[] colors = { new Color(0, 0, 0, 0), new Color(0, 0, 0, 240)};
        float[] dist = {0.05f, .95f};
        m_RadialPaint = new RadialGradientPaint(center, halfWidth + 35, dist, colors, MultipleGradientPaint.CycleMethod.REFLECT);
    }

    void draw(Graphics2D g2d) {
        Paint old = g2d.getPaint();
        g2d.setPaint(m_RadialPaint);
        g2d.fillRect(0, 0, m_Parent.getWidth(), m_Parent.getHeight());
        g2d.setPaint(old);

        m_Name.draw(g2d);

        m_Price.setText(Utils.getPriceAsString(m_Parent.getPrice()));
        m_Price.draw(g2d);
    }
}
