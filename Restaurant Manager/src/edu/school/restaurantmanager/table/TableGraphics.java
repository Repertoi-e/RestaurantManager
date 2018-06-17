package edu.school.restaurantmanager.table;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.util.Utils;

import java.awt.*;

class TableGraphics {
    private Font m_NameLabelFont;
    private Rectangle m_StatusLabelBounds = new Rectangle();

    TableGraphics() {
        m_NameLabelFont = new Font("SourceSansPro", Font.BOLD, 11);
    }

    void updateBounds(int halfWidth, int halfHeight) {
        int nameWidth = Utils.percent(90, 80) / 2;
        int nameHeight = Utils.percent(90, 15) / 2;
        {
            m_StatusLabelBounds.x = halfWidth - 30;
            m_StatusLabelBounds.y = halfHeight - nameHeight;
            m_StatusLabelBounds.width = 60;
            m_StatusLabelBounds.height = nameHeight * 2;
        }
    }

    void draw(Graphics2D g2d, Table table) {
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.translate(0, -10);

        g2d.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
        g2d.fillRect(m_StatusLabelBounds.x, m_StatusLabelBounds.y, m_StatusLabelBounds.width, m_StatusLabelBounds.height);

        g2d.setColor(GlobalColors.TEXT_COLOR);
        Utils.drawCenteredString(g2d, table.m_Name, m_StatusLabelBounds, m_NameLabelFont);

        g2d.translate(0, 20);

        g2d.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
        g2d.fillRect(m_StatusLabelBounds.x, m_StatusLabelBounds.y, m_StatusLabelBounds.width, m_StatusLabelBounds.height);

        g2d.setColor(GlobalColors.TEXT_COLOR);
        Utils.drawCenteredString(g2d, table.Order != null ? Utils.getPriceAsString(table.Order.getTotal()) : "Свободна", m_StatusLabelBounds, m_NameLabelFont);
        g2d.translate(0, -10);
    }
}
