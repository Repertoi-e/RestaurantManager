package edu.school.restaurantmanager;

import java.awt.*;
import java.util.*;

import java.awt.font.TextAttribute;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainHeading extends JPanel {
	
	JLabel m_Label;
	
	public MainHeading() {
		this.setBackground(Color.decode("#1c313a"));
		
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
