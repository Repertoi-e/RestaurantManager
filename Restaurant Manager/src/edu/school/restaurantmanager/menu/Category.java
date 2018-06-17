package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.GlobalColors;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

class Category extends JPanel {

    static final int ITEM_SIZE = 135;
    static final int ITEM_PADDING = 5;
    static final int ITEM_SPACE = ITEM_SIZE + ITEM_PADDING;

    private MenuView m_Parent;
    private ArrayList<MenuItem> m_Items = new ArrayList<>();

    Category(MenuView parent, String name) {
        m_Parent = parent;

        this.setLayout(null);
        this.setBackground(GlobalColors.MENUVIEW_HEAD_COLOR);
        this.setBorder(new TitledBorder(new EtchedBorder(Color.GRAY, GlobalColors.MENUVIEW_HEAD_COLOR), name));
        this.setPreferredSize(new Dimension(135, this.getHeight()));
    }

    void rearrangeItems() {
        int height = m_Parent.getPreferredSize().height - 15 /*scrollbar*/;

        Insets borderInsets = this.getBorder().getBorderInsets(this);

        int itemsColumn = (height - borderInsets.top - borderInsets.bottom) / ITEM_SPACE;

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
            item.setBounds(xOffset + currentColumn * ITEM_SPACE, yOffset + currentRow * ITEM_SPACE, ITEM_SIZE, ITEM_SIZE);
            // След setBounds, задължително updateBounds !!
            item.updateBounds();

            currentRow++;
        }

        this.setSize((currentColumn + 1) * ITEM_SPACE + ITEM_PADDING * 2 + borderInsets.right, height);
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
