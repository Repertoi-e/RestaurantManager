package edu.school.restaurantmanager.frames;


import java.awt.BorderLayout;
import edu.school.restaurantmanager.objects.*;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;
import java.awt.Color;

import static javax.swing.WindowConstants.*;

public class TableViewFrame extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public TableViewFrame(Table table) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnTable = new JButton();
        btnTable.setText(Integer.toString(table.getId()));
        btnTable.setBounds(5, 5, 160, 50);
        contentPane.add(btnTable);
    }
}

