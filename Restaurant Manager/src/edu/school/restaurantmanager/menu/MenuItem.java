package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.table.Table;
import edu.school.restaurantmanager.table.TableViewOrder;
import edu.school.restaurantmanager.table.order.ReceiptEntry;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MenuItem extends JPanel {

    private String m_Name;
    // Цена в стотинки. (ако използваме double/float за цена, ще
    // има грешки при закръгляне: 0.0000000234..)
    private int m_Price;
    private Image m_Image;
    private String m_Category;

    private MenuItemGraphics m_Graphics;
    private MenuItemButton m_Add, m_Remove;

    MenuItem(String name, int price, File image, String category) {
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        setImage(image);
        setCategory(category);
        setName(name);
        setPrice(price);

        m_Graphics = new MenuItemGraphics(this);

        Font buttonFont = new Font("SourceSansPro", Font.BOLD, 20);
        m_Add = new MenuItemButton();
        {
            m_Add.setText("+");
            m_Add.setForeground(GlobalColors.TEXT_COLOR);
            m_Add.setBackground(GlobalColors.MENUITEM_ADD_BG);
            m_Add.setFont(buttonFont);
        }
        m_Add.setVisible(false);
        m_Add.addActionListener(e -> {
            Table table = MainFrame.getTableView().getOrderFrame().getCurrentTable();
            if (table.Order != null)
                table.Order.add(new ReceiptEntry(this, 1));
        });
        this.add(m_Add);

        m_Remove = new MenuItemButton();
        {
            m_Remove.setText("-");
            m_Remove.setForeground(GlobalColors.TEXT_COLOR);
            m_Remove.setBackground(GlobalColors.MENUITEM_REMOVE_BG);
            m_Remove.setFont(buttonFont);
        }
        m_Remove.setVisible(false);
        m_Remove.addActionListener(e -> {
            Table table = MainFrame.getTableView().getOrderFrame().getCurrentTable();
            if (table.Order != null)
                table.Order.add(new ReceiptEntry(this, -1));
        });
        this.add(m_Remove);

        if (MainFrame.getTableView().getOrderFrame().isVisible())
            setButtonsVisible(true);
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

        int buttonSize = 18;

        m_Add.setBounds(10, this.getHeight() - buttonSize - 10, buttonSize, buttonSize);
        m_Remove.setBounds(this.getWidth() - buttonSize - 10, this.getHeight() - buttonSize - 10, buttonSize, buttonSize);
    }

    public void setButtonsVisible(boolean visible)
    {
        m_Add.setVisible(visible);
        m_Remove.setVisible(visible);
    }

    public String getName() { return m_Name; }
    public int getPrice() { return m_Price; }

    public String getCategory() { return m_Category; }

    public void setName(String name) {
        // Ако името е твърде дълго го съкращаваме с ...
        if (name.length() > 21)
        {
            name = name.substring(0, 18);
            name += "...";
        }

        m_Name = name;
        invalidate();
        repaint();
    }

    public void setPrice(int price) {
        m_Price = price;
        invalidate();
        repaint();
    }

    public void setImage(File image) {
        try {
            m_Image = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, image.toString(), "Файлът не е намерен!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCategory(String category) {
        this.m_Category = category;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof MenuItem)
            return ((MenuItem) other).m_Name.equals(m_Name) && ((MenuItem) other).m_Price == m_Price
                    && ((MenuItem) other).m_Image.equals(m_Image);
        return false;
    }
}