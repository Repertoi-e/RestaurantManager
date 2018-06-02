package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.util.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MenuItemGraphics {

    private Image m_Image;
    private Rectangle m_NameLabelBounds = new Rectangle();
    private Rectangle m_PriceLabelBounds = new Rectangle();
    private RadialGradientPaint m_Paint;
    private Font m_NameLabelFont = new Font("SourceSansPro", Font.BOLD, 12);
    private Font m_PriceLabelFont = new Font("SourceSansPro", Font.ITALIC, 12);
    private int nameWidth, nameHeight, priceWidth, priceHeight;

    void updateBounds(int halfWidth, int halfHeight) {
        nameWidth = Utils.percent(halfWidth * 2, 80) / 2;
        nameHeight = Utils.percent(halfHeight * 2, 20) / 2;
        {
            m_NameLabelBounds.x = halfWidth - nameWidth;
            m_NameLabelBounds.y = halfHeight - nameHeight;
            m_NameLabelBounds.width = nameWidth * 2;
            m_NameLabelBounds.height = nameHeight * 2;
        }

        priceWidth = Utils.percent(halfWidth * 2, 50) / 2;
        priceHeight = Utils.percent(halfHeight * 2, 15) / 2;
        {
            m_PriceLabelBounds.x = halfWidth - priceWidth;
            m_PriceLabelBounds.y = halfHeight - priceHeight + nameHeight * 2;
            m_PriceLabelBounds.width = priceWidth * 2;
            m_PriceLabelBounds.height = priceHeight * 2;
        }

        Point2D.Float center = new Point2D.Float(halfWidth, halfHeight);
        Color[] colors = { new Color(0, 0, 0, 0), new Color(0, 0, 0, 240)};
        float[] dist = {0.05f, .95f};
        m_Paint = new RadialGradientPaint(center, halfWidth + 35, dist, colors, MultipleGradientPaint.CycleMethod.REFLECT);
    }

    void setImage(URL imageURL) {
        try {
            m_Image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void draw(Graphics2D g2d, MenuItem item) {
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.drawImage(m_Image, 0, 0, item.getWidth(), item.getHeight(), null);

        Paint old = g2d.getPaint();
        g2d.setPaint(m_Paint);
        g2d.fillRect(0, 0, item.getWidth(), item.getHeight());
        g2d.setPaint(old);

        g2d.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        g2d.fillRect(m_NameLabelBounds.x, m_NameLabelBounds.y, m_NameLabelBounds.width, m_NameLabelBounds.height);
        g2d.fillRect(m_PriceLabelBounds.x, m_PriceLabelBounds.y, m_PriceLabelBounds.width, m_PriceLabelBounds.height);

        // Текст
        {
            Map<TextAttribute, Object> attribs = new HashMap<>();
            attribs.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);

            Rectangle2D r2d = g2d.getFontMetrics(m_NameLabelFont).getStringBounds(item.getName(), g2d);
            float fontSize = (float) (m_NameLabelFont.getSize2D() * nameWidth * 1.9 / r2d.getWidth());
            m_NameLabelFont = m_NameLabelFont.deriveFont(attribs).deriveFont(fontSize);

            String priceText = Utils.getPriceAsString(item.getPrice());

            r2d = g2d.getFontMetrics(m_PriceLabelFont).getStringBounds(priceText, g2d);
            fontSize = (float) (m_PriceLabelFont.getSize2D() * priceWidth * 1.3 / r2d.getWidth());
            m_PriceLabelFont = m_PriceLabelFont.deriveFont(attribs).deriveFont(fontSize);

            g2d.setColor(Color.decode("#eeeeee"));
            Utils.drawCenteredString(g2d, item.getName(), m_NameLabelBounds, m_NameLabelFont);
            Utils.drawCenteredString(g2d, priceText, m_PriceLabelBounds, m_PriceLabelFont);
        }
    }
}
