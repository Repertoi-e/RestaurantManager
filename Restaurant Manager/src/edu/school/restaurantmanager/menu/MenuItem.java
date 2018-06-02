package edu.school.restaurantmanager.menu;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import edu.school.restaurantmanager.util.Utils;

@SuppressWarnings("serial")
public class MenuItem extends JPanel {

    private String m_Name;
    // Цена в стотинки. (ако използваме double/float за цена, ще
    // има грешки при закръгляне: 0.0000000234..)
    private int m_Price;

    Image m_Image;
    Rectangle m_NameLabelBounds = new Rectangle();
    Rectangle m_PriceLabelBounds = new Rectangle();
    RadialGradientPaint m_Paint;
    Font m_NameLabelFont = new Font("SourceSansPro", Font.BOLD, 12);
    Font m_PriceLabelFont = new Font("SourceSansPro", Font.ITALIC, 12);

    int nameWidth;
    int nameHeight;
    int priceWidth;
    int priceHeight;

    MenuItem(String name, int price, URL imageURL) {
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        setImage(imageURL);

        setName(name);
        setPrice(price);
    }

    void updateBounds() {
        int width = this.getWidth() / 2;
        int height = (int) ((double)this.getHeight() / 2.35);

        nameWidth = Utils.percent(this.getWidth(), 80) / 2;
        nameHeight = Utils.percent(this.getHeight(), 20) / 2;
        {
            m_NameLabelBounds.x = width - nameWidth;
            m_NameLabelBounds.y = height - nameHeight;
            m_NameLabelBounds.width = nameWidth * 2;
            m_NameLabelBounds.height = nameHeight * 2;
        }

        priceWidth = Utils.percent(this.getWidth(), 50) / 2;
        priceHeight = Utils.percent(this.getHeight(), 15) / 2;
        {
            m_PriceLabelBounds.x = width - priceWidth;
            m_PriceLabelBounds.y = height - priceHeight + nameHeight * 2;
            m_PriceLabelBounds.width = priceWidth * 2;
            m_PriceLabelBounds.height = priceHeight * 2;
        }

        Point2D.Float center = new Point2D.Float(width, height);
        Color[] colors = { new Color(0, 0, 0, 0), new Color(0, 0, 0, 240)};
        float[] dist = {0.05f, .95f};
        m_Paint = new RadialGradientPaint(center, width + 35, dist, colors, MultipleGradientPaint.CycleMethod.REFLECT);
    }

    public String getName() { return m_Name; }
    public int getPrice() { return m_Price; }

    public void setName(String name) { 
        m_Name = name;
        invalidate();
        repaint();
    }

    public void setPrice(int price) { 
        m_Price = price;
        invalidate();
        repaint();
    }

    public void setImage(URL imageURL) {
        try {
			m_Image = ImageIO.read(imageURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.drawImage(m_Image, 0, 0, this.getWidth(), this.getHeight(), null);

        Paint old = g2d.getPaint();
        g2d.setPaint(m_Paint);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setPaint(old);

        g2d.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        g2d.fillRect(m_NameLabelBounds.x, m_NameLabelBounds.y, m_NameLabelBounds.width, m_NameLabelBounds.height);
        g2d.fillRect(m_PriceLabelBounds.x, m_PriceLabelBounds.y, m_PriceLabelBounds.width, m_PriceLabelBounds.height);

        // Текст
        {
            Map<TextAttribute, Object> attribs = new HashMap<>();
            attribs.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);

            Rectangle2D r2d = g2d.getFontMetrics(m_NameLabelFont).getStringBounds(m_Name, g2d);
            float fontSize = (float) (m_NameLabelFont.getSize2D() * nameWidth * 1.9 / r2d.getWidth());
            m_NameLabelFont = m_NameLabelFont.deriveFont(attribs).deriveFont(fontSize);

            String priceText = Utils.getPriceAsString(m_Price);

            r2d = g2d.getFontMetrics(m_PriceLabelFont).getStringBounds(priceText, g2d);
            fontSize = (float) (m_PriceLabelFont.getSize2D() * priceWidth * 1.3 / r2d.getWidth());
            m_PriceLabelFont = m_PriceLabelFont.deriveFont(attribs).deriveFont(fontSize);

            g2d.setColor(Color.decode("#eeeeee"));
            Utils.drawCenteredString(g2d, m_Name, m_NameLabelBounds, m_NameLabelFont);
            Utils.drawCenteredString(g2d, priceText, m_PriceLabelBounds, m_PriceLabelFont);
        }
    }
}