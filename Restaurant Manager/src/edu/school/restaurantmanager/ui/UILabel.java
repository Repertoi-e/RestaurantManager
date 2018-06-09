package edu.school.restaurantmanager.ui;

import edu.school.restaurantmanager.util.Utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UILabel {

    private String m_Text;
    private Font m_Font;
    private Rectangle m_Bounds = new Rectangle();
    private Color m_Foreground;
    private float m_WidthScale;

    public UILabel(Font font, float widthScale)
    {
        this(font, " ", widthScale);
    }

    public UILabel(Font font, String text, float widthScale)
    {
        m_Text = text;
        m_Font = font;

        m_WidthScale = widthScale;
    }

    public void setText(String text)
    {
        m_Text = text;
    }

    public void setBounds(int x, int y, int width, int height)
    {
        m_Bounds.setBounds(x, y, width, height);
    }

    public void setForeground(Color foreground)
    {
        m_Foreground = foreground;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        g2d.fillRect(m_Bounds.x, m_Bounds.y, m_Bounds.width, m_Bounds.height);

        Rectangle2D r2d = g2d.getFontMetrics(m_Font).getStringBounds(m_Text, g2d);
        float fontSize = (float) (m_Font.getSize2D() * m_Bounds.width / 2 * m_WidthScale / r2d.getWidth());
        Font renderFont = m_Font.deriveFont(fontSize);

        g2d.setColor(m_Foreground);
        Utils.drawCenteredString(g2d, m_Text, m_Bounds, renderFont);
    }
}
