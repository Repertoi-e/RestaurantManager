package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.GlobalColors;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

class Category extends JPanel {

    static final int ITEM_WIDTH = 135;
    static final int ITEM_HEIGHT = 90;
    static final int ITEM_PADDING = 5;
    static final int ITEM_SPACEX = ITEM_WIDTH + ITEM_PADDING;
    static final int ITEM_SPACEY = ITEM_HEIGHT + ITEM_PADDING;

    private MenuView m_Parent;
    private ArrayList<MenuItem> m_Items = new ArrayList<>();

    Category(MenuView parent, String name) {
        m_Parent = parent;

        Font titleFont = new Font("SourceSansPro", Font.BOLD, 14);

        this.setLayout(null);
        this.setBackground(GlobalColors.MENUVIEW_HEAD_COLOR);
        this.setBorder(new TitledBorder(new EtchedBorder(Color.GRAY, GlobalColors.MENUVIEW_HEAD_COLOR), name, TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, titleFont, GlobalColors.TEXT_COLOR));
        this.setPreferredSize(new Dimension(135, this.getHeight()));
    }

    void rearrangeItems() {
        int height = m_Parent.getPreferredSize().height - 15 /*scrollbar*/;

        Insets borderInsets = this.getBorder().getBorderInsets(this);

        int itemsColumn = (height - borderInsets.top - borderInsets.bottom) / ITEM_SPACEY;

        int currentColumn = 0, currentRow = 0;
        for (MenuItem item : m_Items) {
            if (currentRow == itemsColumn)
            {
                currentRow = 0;
                currentColumn++;
            }

            int xOffset = ITEM_PADDING + borderInsets.left;
            int yOffset = ITEM_PADDING + borderInsets.top;
            // Тук е мястото и размера
            item.setBounds(xOffset + currentColumn * ITEM_SPACEX, yOffset + currentRow * ITEM_SPACEY, ITEM_WIDTH, ITEM_HEIGHT);
            // След setBounds, задължително updateBounds !!
            item.updateBounds();

            currentRow++;
        }

        this.setSize((currentColumn + 1) * ITEM_SPACEX + ITEM_PADDING * 2 + borderInsets.right, height);
    }

    void setButtonsVisible(boolean visible) {
        for (MenuItem item : m_Items)
            item.setButtonsVisible(visible);
    }

    void add(MenuItem item) {
        this.add((JPanel) item);
        m_Items.add(item);

        this.invalidate();
        this.repaint();
    }
}
