package edu.school.restaurantmanager.workfile;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

// Прозорец, с който се променя съдържанието на менюто.

@SuppressWarnings("serial")
public class WorkFile extends JFrame {
    private static final int Width = 600, Height = 500;

    private File m_ImageDir = null;
    private JTextArea m_TextArea;
    private JFileChooser m_DirectoryChooser = new JFileChooser();

    public WorkFile() {
        this.setTitle("Меню");
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setSize(new Dimension(
                WorkFile.Width,
                WorkFile.Height));
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                MainFrame.getMenuView().updateItems(m_ImageDir, m_TextArea.getText());
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(null);
        contentPane.setBackground(GlobalColors.WORKFILE_BACKGROUND);
        this.setContentPane(contentPane);

        m_TextArea = new JTextArea("", 13, 50);
        m_TextArea.setBackground(GlobalColors.WORKFILE_BACKGROUND.brighter());
        m_TextArea.setForeground(GlobalColors.TEXT_COLOR);
        m_TextArea.setCaretColor(GlobalColors.TEXT_COLOR);

        setTextAreaData();

        JScrollPane scroll = new JScrollPane(m_TextArea);
        scroll.setBounds(10, 10, Width - 24, Height - 80);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scroll);

        BufferedImage icon = null;
        try
        {
            icon = ImageIO.read(WorkFile.class.getResource("open-icon.png"));
        } catch (Exception e) { /* ¯\_(ツ)_/¯ */}

        JButton openButton = new JButton();

        int buttonSize = 32;
        if (icon != null)
            openButton.setIcon(new ImageIcon(icon.getScaledInstance(buttonSize - 5, -1, Image.SCALE_SMOOTH)));
        openButton.setBackground(GlobalColors.TABLEVIEW_BG_COLOR);
        openButton.addActionListener(e -> chooseImageDirectory());
        openButton.setBounds(10, Height - 2 * buttonSize, buttonSize, buttonSize);
        contentPane.add(openButton);
    }

    public File getImageDir() { return m_ImageDir; }

    public void setTextAreaData(){
        File file = new File("Menu.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = br.readLine()) != null){
                m_TextArea.append(line+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean chooseImageDirectory() {
        m_DirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = m_DirectoryChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            m_ImageDir = m_DirectoryChooser.getSelectedFile();
            return true;
        }
        return false;
    }
}
