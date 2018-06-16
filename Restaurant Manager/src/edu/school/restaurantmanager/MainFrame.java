package edu.school.restaurantmanager;

import edu.school.restaurantmanager.menu.MenuItemButton;
import edu.school.restaurantmanager.menu.MenuView;
import edu.school.restaurantmanager.util.Utils;
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
    private JButton m_EditWorkFileButton = null, m_EditTableLayoutButton = null, m_AddTableButton;
    private WorkFile m_WorkFile = new WorkFile();

	// При първо пускане или когато размерът на прозореца се промени
	public void rebuildUI() {
        int buttonSize = 30;
        BufferedImage editIcon = null;
        try
        {
            editIcon = ImageIO.read(WorkFile.class.getResource("edit-icon.png"));
        } catch (Exception e) { /* ¯\_(ツ)_/¯ */}
        ImageIcon buttonIcon = new ImageIcon(editIcon.getScaledInstance(buttonSize - 5, -1, Image.SCALE_SMOOTH));

        // Бутон за work file-а
        {
            if (m_EditWorkFileButton == null)
            {
                m_EditWorkFileButton = new JButton();
                m_EditWorkFileButton.setIcon(buttonIcon);
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

        // Бутон за подредбата на масите
        {
            if (m_EditTableLayoutButton == null)
            {
                m_EditTableLayoutButton = new JButton();
                m_EditTableLayoutButton.setIcon(buttonIcon);
                m_EditTableLayoutButton.setBackground(GlobalColors.EDITTABLELAYOUT_OFF);
                m_EditTableLayoutButton.addActionListener(e -> m_TableView.setEditing(!m_TableView.isEditing()));
                m_ContentPane.add(m_EditTableLayoutButton);
            }

            m_EditTableLayoutButton.setBounds(Utils.percent(this.getWidth(), 60) - buttonSize - 7, 5, buttonSize, buttonSize);
        }

        // Бутон за добавяне на нова маса
        {
            if (m_AddTableButton == null)
            {
                Font buttonFont = new Font("SourceSansPro", Font.BOLD, 20);
                m_AddTableButton = new MenuItemButton();
                m_AddTableButton.setText("+");
                m_AddTableButton.setForeground(GlobalColors.TEXT_COLOR);
                m_AddTableButton.setBackground(GlobalColors.MENUITEM_ADD_BG);
                m_AddTableButton.setFont(buttonFont);
                m_AddTableButton.setVisible(false);
                m_ContentPane.add(m_AddTableButton);
            }
            m_AddTableButton.setBounds(m_EditTableLayoutButton.getX() - buttonSize - 7, 5, buttonSize, buttonSize);
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
		Instance = this; // Имаме само един MainFrame в цялата програма

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

    public static MenuView getMenuView()
    {
        return Instance.m_MenuView;
    }
    public static TableView getTableView()
    {
        return Instance.m_TableView;
    }
    public static JButton getEditTableLayoutButton() { return Instance.m_EditTableLayoutButton; }
    public static JButton getAddTableButton() { return Instance.m_AddTableButton; }

	public static void main(String[] args) { new MainFrame().setVisible(true); }
}
