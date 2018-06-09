package edu.school.restaurantmanager.table;

import edu.school.restaurantmanager.util.Utils;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class TableStatus {

    private Font m_NameLabelFont;
    private Rectangle m_StatusLabelBounds = new Rectangle();

    public TableStatus() {
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

        TableUnavailableInfo info = table.UnavailableInfo;

        if (info != null) {
            g2d.translate(0, -10);

            g2d.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
            g2d.fillRect(m_StatusLabelBounds.x, m_StatusLabelBounds.y, m_StatusLabelBounds.width, m_StatusLabelBounds.height);

            g2d.setColor(Color.decode("#eeeeee"));
            Utils.drawCenteredString(g2d, Utils.getBookHourAsString(info), m_StatusLabelBounds, m_NameLabelFont);

            g2d.translate(0, 20);

            g2d.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
            g2d.fillRect(m_StatusLabelBounds.x, m_StatusLabelBounds.y, m_StatusLabelBounds.width, m_StatusLabelBounds.height);

            g2d.setColor(Color.decode("#eeeeee"));
            Utils.drawCenteredString(g2d, Utils.getPriceAsString(info.Order.Total), m_StatusLabelBounds, m_NameLabelFont);
            g2d.translate(0, -10);
        } else {
            g2d.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
            g2d.fillRect(m_StatusLabelBounds.x, m_StatusLabelBounds.y, m_StatusLabelBounds.width, m_StatusLabelBounds.height);

            g2d.setColor(Color.decode("#a4f442"));
            Utils.drawCenteredString(g2d, "Свободна", m_StatusLabelBounds, m_NameLabelFont);
        }
    }
}
