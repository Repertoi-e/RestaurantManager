package edu.school.restaurantmanager.frames;

import edu.school.restaurantmanager.objects.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class TableViewFrame extends JFrame {
	JPanel m_ContentPane;

    // Default constructor
    public TableViewFrame(Table table) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 450, 450);
        
        m_ContentPane = new JPanel();
        m_ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        m_ContentPane.setLayout(null);
        this.setContentPane(m_ContentPane);
        
        JButton btnTable = new JButton();
        btnTable.setText(Integer.toString(table.id));
        btnTable.setBounds(5, 5, 160, 50);
        m_ContentPane.add(btnTable);
    }
}

