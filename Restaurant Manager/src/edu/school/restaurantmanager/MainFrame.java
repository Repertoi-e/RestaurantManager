package edu.school.restaurantmanager;

import edu.school.restaurantmanager.menu.MenuView;
import edu.school.restaurantmanager.table.TableView;
import edu.school.restaurantmanager.util.Fonts;
import edu.school.restaurantmanager.util.MainHeading;
import edu.school.restaurantmanager.util.ResizeListener;
import edu.school.restaurantmanager.util.Utils;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	// Размерът на прозореца
	private static final int Width = 960, Height = 540;
	
	private static MainFrame Instance;
	
	private JPanel m_ContentPane;
	private MainHeading m_MainHeader = null;
	private TableView m_TableView = null;
	private MenuView m_MenuView = null;
	
	// При първо пускане или когато размерът на прозореца се промени
	public void rebuildUI() {
		// Заглавието
		{
			// Вместо hard-code-нат размер, взимаме процент от размера на целия прозорец.
			final int width = Utils.percent(this.getWidth(), 60);
			final int height = 40;
						
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
			final int width = Utils.percent(this.getWidth(), 60);
			final int height = this.getHeight() - 40;
					
			if (m_TableView == null)
			{
				m_TableView = new TableView();
				m_ContentPane.add(m_TableView);
			}
			
			m_TableView.setBounds(0, this.getHeight() - height, width, height);
		}
		
		// Полето с менюто
		{
			final int width = Utils.percent(this.getWidth(), 40);
			final int height = this.getHeight();
					
			if (m_MenuView == null)
			{
				m_MenuView = new MenuView();
				m_ContentPane.add(m_MenuView);
			}
			
			m_MenuView.setBounds(this.getWidth() - width, 0, width, height);
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
	
	public static void main(String[] args) throws Exception { new MainFrame().setVisible(true); }
}
