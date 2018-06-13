package edu.school.restaurantmanager;

import edu.school.restaurantmanager.menu.MenuView;
import edu.school.restaurantmanager.workfile.DirWatcher;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainFrame extends JFrame {
	
	// Размерът на прозореца
	private static final int Width = 960, Height = 540;
	
	private static MainFrame Instance;
	
	private JPanel m_ContentPane;
	private TableView m_TableView = null;
	private MenuView m_MenuView = null;
    private JButton m_OpenWorkFileButton = null;
    private WorkFile m_WorkFile = new WorkFile();
    private DirWatcher m_DirWatcher;
    final JFileChooser m_FileChooser = new JFileChooser();

    private void chooseWorkFile() {
        int result = m_FileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = m_FileChooser.getSelectedFile();
            System.out.println("Changing work file: " + file.getAbsolutePath());
            m_WorkFile.updateFile(file);
        } else {
            JOptionPane.showMessageDialog(null, "Моля избери файл с настройки.", "Грешка", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

	// При първо пускане или когато размерът на прозореца се промени
	public void rebuildUI() {
        // Бутон за файла
        {
            int buttonSize = 40;
            if (m_OpenWorkFileButton == null)
            {
                BufferedImage icon = null;
                try
                {
                    icon = ImageIO.read(WorkFile.class.getResource("open-icon.png"));
                } catch (Exception e) { /* ¯\_(ツ)_/¯ */}

                m_OpenWorkFileButton = new JButton();

                m_OpenWorkFileButton.setIcon(new ImageIcon(icon.getScaledInstance(buttonSize - 5, -1, java.awt.Image.SCALE_SMOOTH)));
                m_OpenWorkFileButton.setBackground(TableView.BACKGROUND_COLOR);
                m_OpenWorkFileButton.addActionListener(e -> {
                    int result = m_FileChooser.showOpenDialog(this);
                    if (result == JFileChooser.APPROVE_OPTION)
                    {
                        File file = m_FileChooser.getSelectedFile();
                        System.out.println("Changing work file: " + file.getAbsolutePath());
                        m_WorkFile.updateFile(file);
                    }
                    else
                        System.out.println("No file chosen!");
                });
                m_ContentPane.add(m_OpenWorkFileButton);
            }

            m_OpenWorkFileButton.setBounds(10, this.getHeight() - 8 - 2 * buttonSize, buttonSize, buttonSize);
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

        try {
            m_DirWatcher = new DirWatcher();
            // Взимаме директорията на файла с настройките.
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(m_DirWatcher);
            executor.shutdown();
        } catch (Exception e) { e.printStackTrace(); }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Restaurant files", "restaurant");
        m_FileChooser.setFileFilter(filter);
        chooseWorkFile();

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

    public static WorkFile getWorkFile() {
        return Instance.m_WorkFile;
    }
    public static DirWatcher getDirWatcher() {
        return Instance.m_DirWatcher;
    }
    public static MenuView getMenuView()
    {
        return Instance.m_MenuView;
    }

	public static void main(String[] args) { new MainFrame().setVisible(true); }
}
