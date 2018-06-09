package edu.school.restaurantmanager.table;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

// Полето, където се показват масите.

public class TableView extends JPanel {

	public static final Color BACKGROUND_COLOR = Color.decode("#455a64");
    public static final Color HEADING_COLOR = Color.decode("#1c313a");

    JPanel m_Heading;

    boolean m_EditMode = false;
	
	public TableView() {
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(null);

        m_Heading = new JPanel();
        m_Heading.setBackground(HEADING_COLOR);
        m_Heading.setLayout(null);
        {
            Font font = new Font("SourceSansPro", Font.ITALIC | Font.BOLD, 25);
            Map<TextAttribute, Object> fontAttribs = new HashMap<>(font.getAttributes());
            fontAttribs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

            JLabel label = new JLabel("Маси");
            label.setFont(font.deriveFont(fontAttribs));
            label.setForeground(Color.decode("#eeeeee"));
            m_Heading.add(label);
        }
        this.add(m_Heading);
		
		// Пример за жълта маса
		TableColorPalette yellow = new TableColorPalette();
		{
			yellow.Top = Color.decode("#fdd835");
			yellow.Chair = Color.decode("#fbc02d");
			yellow.ChairShadow = Color.decode("#e8b228");
		}

		this.add(new Table(100, 100, 180, 250, yellow));
		this.add(new Table(300, 350, 90, 90, yellow));
	}

    public JPanel getHeading() { return m_Heading; }
}
