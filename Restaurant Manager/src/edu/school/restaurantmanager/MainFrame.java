package edu.school.restaurantmanager;

import edu.school.restaurantmanager.menu.MenuView;
import edu.school.restaurantmanager.table.TableView;
import edu.school.restaurantmanager.util.Utils;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	// Размерът на прозореца
	public static final int Width = 960, Height = 540;
	
	public static MainFrame Instance;
	
	JPanel m_ContentPane;
	MainHeading m_MainHeader = null;
	TableView m_TableView = null;
	MenuView m_MenuView = null;
	
	// При първо пускане или когато размерът на прозореца се промени
	public void rebuildUI() {
		// Заглавието
		{
			// Вместо hard-code-нат размер, взимаме процент от размера на целия прозорец.
			int width = Utils.percent(this.getWidth(), 60);
			int height = 40;
						
			if (m_MainHeader == null)
			{
				m_MainHeader = new MainHeading();
				m_ContentPane.add(m_MainHeader);
			}
			
			m_MainHeader.setBounds(0, 0, width, height);
			m_MainHeader.getLabel().setBounds(10, 0, width, height);
		}
		
		// Полето с масите
		{
			int width = Utils.percent(this.getWidth(), 60);
			int height = this.getHeight() - 40;
					
			if (m_TableView == null)
			{
				m_TableView = new TableView();
				m_ContentPane.add(m_TableView);
			}
			
			m_TableView.setBounds(0, this.getHeight() - height, width, height);
		}
		
		// Полето с менюто
		{
			int width = Utils.percent(this.getWidth(), 40);
			int height = this.getHeight();
					
			if (m_MenuView == null)
			{
				m_MenuView = new MenuView();
				m_ContentPane.add(m_MenuView);
			}
			
			m_MenuView.setBounds(this.getWidth() - width, 0, width, height);
			m_MenuView.getHeading().setBounds(0, 0, width, 40);
		}
	}

	public MainFrame() {
		Instance = this;
		
		Fonts.registerFonts();
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(new Dimension(
				MainFrame.Width, 
				MainFrame.Height));
		this.setMinimumSize(new Dimension(
				(int) ((double) MainFrame.Width * 0.2), 
				(int) ((double) MainFrame.Height * 0.2)));
		this.setLocationRelativeTo(null);
		this.addComponentListener(new ResizeListener());
		
		m_ContentPane = new JPanel();
		m_ContentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		m_ContentPane.setLayout(null);
		this.setContentPane(m_ContentPane);
	}
	
	public static void main(String[] args) { new MainFrame().setVisible(true); }
}
