package edu.school.restaurantmanager.menu;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MenuItem extends JPanel {

    private String m_Name;
    // Цена в стотинки. (ако използваме double/float за цена, ще
    // има грешки при закръгляне: 0.0000000234..)
    private int m_Price;
    private Image m_Image;

    private MenuItemGraphics m_Graphics;
    private MenuItemButton m_Add, m_Remove;
    //

    MenuItem(String name, int price, URL imageURL) {
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        setImage(imageURL);

        setName(name);
        setPrice(price);

        m_Graphics = new MenuItemGraphics(this);

        Font buttonFont = new Font("SourceSansPro", Font.BOLD, 20);
        m_Add = new MenuItemButton();
        {
            m_Add.setText("+++++++++++++++++++++++++++++++++++++");
            m_Add.setForeground(Color.decode("#eeeeee"));
            m_Add.setBackground(Color.green);
            m_Add.setFont(buttonFont);
        }
        this.add(m_Add);

        m_Remove = new MenuItemButton();
        {
            m_Remove.setText("-++++++++++++++++++++++++++++++++++++");
            m_Remove.setForeground(Color.decode("#eeeeee"));
            m_Remove.setBackground(Color.red);
            m_Remove.setFont(buttonFont);
        }
        m_Remove.addActionListener(e -> {

        });
        this.add(m_Remove);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.drawImage(m_Image, 0, 0, this.getWidth(), this.getHeight(), null);

        m_Graphics.draw(g2d);
    }

    void updateBounds() {
        m_Graphics.updateBounds(this.getWidth() / 2, (int) ((double)this.getHeight() / 2.35));

        int buttonSize = 30;

        m_Add.setBounds(10, this.getHeight() - buttonSize - 10, buttonSize, buttonSize);
        m_Remove.setBounds(this.getWidth() - buttonSize - 10, this.getHeight() - buttonSize - 10, buttonSize, buttonSize);
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
}