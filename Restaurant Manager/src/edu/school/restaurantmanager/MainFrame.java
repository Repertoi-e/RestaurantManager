package edu.school.restaurantmanager;

import edu.school.restaurantmanager.menu.MenuView;
import edu.school.restaurantmanager.table.TableView;
import edu.school.restaurantmanager.util.Utils;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	// Размера на прозореца
	public static final int Width = 960, Height = 540;
	
	public static MainFrame Instance;
	
	JPanel m_ContentPane;
	TableView m_TableView = null;
	MenuView m_MenuView = null;
	
	// При първо пускане или когато размерът на прозореца се промени.
	public void RebuildUI() {
		// Полето с масите
		{
			// Да заема 60% хоризонтално място и 95% вертикално.
			// Вместо hard-code-нат размер (пр.:  576 x 513px, което
			// не изглежда добре, когато променяш размера на прозореца.)
			final int width = Utils.GetPercentOfInteger(this.getWidth(), 60);
			final int height = Utils.GetPercentOfInteger(this.getHeight(), 95);
					
			if (m_TableView == null)
			{
				m_TableView = new TableView();
				m_TableView.setBackground(Color.RED);
				m_ContentPane.add(m_TableView);
			}
			
			m_TableView.setBounds(0, this.getHeight() - height, width, height);
		}
		
		// Полето с менюто
		{
			final int width = Utils.GetPercentOfInteger(this.getWidth(), 40);
			final int height = this.getHeight();
					
			if (m_MenuView == null)
			{
				m_MenuView = new MenuView();
				m_MenuView.setBackground(Color.BLUE);
				m_ContentPane.add(m_MenuView);
			}
			
			m_MenuView.setBounds(this.getWidth() - width, 0, width, height);
		}
	}

	public MainFrame() {
		Instance = this;
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(new Dimension(
				MainFrame.Width, 
				MainFrame.Height));
		this.setMinimumSize(new Dimension(
				(int) ((double) MainFrame.Width * 0.2), 
				(int) ((double) MainFrame.Height * 0.2)));
		this.setLocationRelativeTo(null);
		
		m_ContentPane = new JPanel();
		m_ContentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		m_ContentPane.setLayout(null);
		m_ContentPane.addComponentListener(new ResizeListener());
		this.setContentPane(m_ContentPane);
	}
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new MainFrame().setVisible(true);
	}
}
