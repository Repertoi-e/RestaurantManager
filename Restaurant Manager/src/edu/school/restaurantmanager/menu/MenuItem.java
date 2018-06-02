package edu.school.restaurantmanager.menu;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

@SuppressWarnings("serial")
public class MenuItem extends JPanel {

    private String m_Name;
    // Цена в стотинки. (ако използваме double/float за цена, ще
    // има грешки при закръгляне: 0.0000000234..)
    private int m_Price;

    private MenuItemGraphics m_Graphics = new MenuItemGraphics();

    MenuItem(String name, int price, URL imageURL) {
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        setImage(imageURL);

        setName(name);
        setPrice(price);
    }

    void updateBounds() {
        m_Graphics.updateBounds(this.getWidth() / 2, (int) ((double)this.getHeight() / 2.35));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        m_Graphics.draw((Graphics2D) g, this);
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
        m_Graphics.setImage(imageURL);
    }
}