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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

// Прозорец, с който се променя съдържанието на менюто.

@SuppressWarnings("serial")
public class WorkFile extends JFrame {
    private static final int Width = 600, Height = 500;

    File m_File = null;
    JTextArea m_TextArea;
    JFileChooser m_FileChooser = new JFileChooser();

    public WorkFile() {
        this.setTitle("Work file");
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
                MainFrame.getMenuView().updateItems(m_File, m_TextArea.getText());

                // Запаметява промените в файла
                try {
                    Files.write(m_File.toPath(), m_TextArea.getText().getBytes());
                } catch (IOException e) { e.printStackTrace(); }
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(null);
        contentPane.setBackground(GlobalColors.WORKFILE_BACKGROUND);
        this.setContentPane(contentPane);

        m_TextArea = new JTextArea("# Input products here!\n", 13, 50);
        m_TextArea.setBackground(GlobalColors.WORKFILE_BACKGROUND.brighter());
        m_TextArea.setForeground(GlobalColors.TEXT_COLOR);
        m_TextArea.setCaretColor(GlobalColors.TEXT_COLOR);

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
        openButton.addActionListener(e -> chooseWorkFile());
        openButton.setBounds(10, Height - 2 * buttonSize, buttonSize, buttonSize);
        contentPane.add(openButton);
    }

    public File getFile() { return m_File; }

    // !!!
    // Може да няма промени, но е сложно да се провери, затова просто питаме всеки път.
    public void askForUnsavedChanges() {
        if (m_File != null)
        {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Да бъдат ли запазени промените в файла?", "Незапазени промени.", JOptionPane.YES_NO_OPTION);
            if(dialogResult == 0) {
                try {
                    Files.write(m_File.toPath(), m_TextArea.getText().getBytes());
                } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }

    public void chooseWorkFile() {
        int result = m_FileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            askForUnsavedChanges();
            m_File = m_FileChooser.getSelectedFile();
            try {
                m_TextArea.setText(new String(Files.readAllBytes(m_File.toPath())));
            } catch (IOException e) { e.printStackTrace(); }
        } else if (m_File == null) {
            JOptionPane.showMessageDialog(null, "Моля, изберете файл, където да бъдат запазени менюто и подредбата на масите.", "Грешка", JOptionPane.ERROR_MESSAGE);
            chooseWorkFile();
        }
    }
}
