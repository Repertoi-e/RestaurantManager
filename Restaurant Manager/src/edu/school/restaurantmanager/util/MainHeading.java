package edu.school.restaurantmanager.util;

import java.awt.*;
import java.util.*;

import java.awt.font.TextAttribute;

import javax.swing.*;

// Полето над масите, което показва заглавието

@SuppressWarnings("serial")
public class MainHeading extends JPanel {
	
	public static final Color BACKGROUND_COLOR = Color.decode("#1c313a");

	JLabel m_Label;
	
	public MainHeading() {
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(null);
		
		Font font = new Font("SourceSansPro", Font.ITALIC | Font.BOLD, 25);
		Map<TextAttribute, Object> fontAttribs = new HashMap<>(font.getAttributes());
		fontAttribs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		
		m_Label = new JLabel("Маси");
		m_Label.setFont(font.deriveFont(fontAttribs));
		m_Label.setForeground(Color.decode("#eeeeee"));
		this.add(m_Label);
	}
	
	public JLabel getLabel() { return m_Label; }
}
