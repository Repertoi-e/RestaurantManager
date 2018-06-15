package edu.school.restaurantmanager;

import edu.school.restaurantmanager.menu.MenuView;
import edu.school.restaurantmanager.workfile.WorkFile;
import edu.school.restaurantmanager.table.TableView;
import edu.school.restaurantmanager.util.Fonts;
import edu.school.restaurantmanager.util.ResizeListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFrame extends JFrame {
	
	// Размерът на прозореца
	private static final int Width = 960, Height = 540;
	
	private static MainFrame Instance;
	
	private JPanel m_ContentPane;
	private TableView m_TableView = null;
	private MenuView m_MenuView = null;
    private JButton m_EditWorkFileButton = null;
    private WorkFile m_WorkFile = new WorkFile();

	// При първо пускане или когато размерът на прозореца се промени
	public void rebuildUI() {
        // Бутон за work file-а
        {
            int buttonSize = 30;
            if (m_EditWorkFileButton == null)
            {
                BufferedImage icon = null;
                try
                {
                    icon = ImageIO.read(WorkFile.class.getResource("edit-icon.png"));
                } catch (Exception e) { /* ¯\_(ツ)_/¯ */}

                m_EditWorkFileButton = new JButton();

                if (icon != null)
                    m_EditWorkFileButton.setIcon(new ImageIcon(icon.getScaledInstance(buttonSize - 5, -1, Image.SCALE_SMOOTH)));
                m_EditWorkFileButton.setBackground(GlobalColors.TABLEVIEW_BG_COLOR);
                m_EditWorkFileButton.addActionListener(e ->
                {
                    if (m_WorkFile.getFile() == null)
                        m_WorkFile.chooseWorkFile();
                    m_WorkFile.setVisible(true);
                });
                m_ContentPane.add(m_EditWorkFileButton);
            }

            m_EditWorkFileButton.setBounds(this.getWidth() - buttonSize * 2 + 7, 5, buttonSize, buttonSize);
        }

		// Полето с масите
		{
			if (m_TableView == null)
			{
				m_TableView = new TableView();
				m_ContentPane.add(m_TableView);
			}
            m_TableView.updateBounds(this.getWidth(), this.getHeight());
        }
		
		// Полето с менюто
		{
			if (m_MenuView == null)
			{
				m_MenuView = new MenuView(this.getHeight());
				m_ContentPane.add(m_MenuView);
			}
            m_MenuView.updateBounds(this.getWidth(), this.getHeight());
        }
	}

	private MainFrame() {
		Instance = this;

		Fonts.registerFonts();

        m_WorkFile.setVisible(false);

        this.setTitle("Restaurant manager"); // Колко време седим без заглавие!!!
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

    public static WorkFile getWorkFile() {
        return Instance.m_WorkFile;
    }
    public static MenuView getMenuView()
    {
        return Instance.m_MenuView;
    }

	public static void main(String[] args) { new MainFrame().setVisible(true); }
}
