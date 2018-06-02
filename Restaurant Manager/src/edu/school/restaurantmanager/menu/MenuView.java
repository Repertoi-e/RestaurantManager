package edu.school.restaurantmanager.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

// Полето, където се показва менюто.

@SuppressWarnings("serial")
public class MenuView extends JPanel {

	public static final Color BACKGROUND_COLOR = Color.decode("#718792");

	JPanel m_Heading;

	public MenuView() {
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(null);

		m_Heading = new JPanel();
		m_Heading.setBackground(BACKGROUND_COLOR.darker());
		m_Heading.setLayout(null);
		{

			Font font = new Font("SourceSansPro", Font.ITALIC | Font.BOLD, 25);
			Map<TextAttribute, Object> fontAttribs = new HashMap<>(font.getAttributes());
			fontAttribs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

			JLabel label = new JLabel("Mеню");
			label.setBounds(10, 10, 100, 25);
			label.setFont(font.deriveFont(fontAttribs));
			label.setForeground(Color.decode("#eeeeee"));
			m_Heading.add(label);
		}
		this.add(m_Heading);

		// Всеки MenuItem има име, цена и URL с снимка.
		MenuItem item = new MenuItem("Пържени картофки", 320, MenuView.class.getResource("french_fries.png"));
		// Тук е мястото и размера в менюто
		item.setBounds(10,50, 135, 135);
		// След setBounds, задължително updateBounds !!
		item.updateBounds();
		// MenuItem extend-ва JPanel
		this.add(item);
	}

	public JPanel getHeading() { return m_Heading; }
}
