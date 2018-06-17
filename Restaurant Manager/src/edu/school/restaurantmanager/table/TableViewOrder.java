package edu.school.restaurantmanager.table;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.menu.MenuItem;
import edu.school.restaurantmanager.menu.MenuView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TableViewOrder extends JFrame {

    private Table m_CurrentTable;
    private JTextArea m_TableName = new JTextArea();;
    private JEditorPane m_PaneReceipt = new JEditorPane();

    TableViewOrder() {
        this.setTitle("Сметка");
        this.setLayout(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(333, 555);
        this.setResizable(false);
        this.setBackground(GlobalColors.TABLEVIEW_BG_COLOR.brighter());
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                MainFrame.getMenuView().setButtonsVisible(false);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                MainFrame.getMenuView().setButtonsVisible(true);
            }
        });

        m_PaneReceipt.setContentType("text/html");
        m_PaneReceipt.setEditable(false);

        m_TableName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                m_CurrentTable.setName(m_TableName.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                m_CurrentTable.setName(m_TableName.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                m_CurrentTable.setName(m_TableName.getText());
            }
        });
        m_TableName.setFont(m_TableName.getFont().deriveFont(24));
        m_TableName.setBounds(10, 10, 313, 20);
        this.add(m_TableName);

        m_PaneReceipt.setBounds(10, 35, 314, 402);
        JScrollPane scrollPane = new JScrollPane(m_PaneReceipt);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 35, 314, 402);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        this.add(scrollPane);

        JButton btnOpenReceiptLog = new JButton("Отвори сметка");
        btnOpenReceiptLog.setBounds(10, 440, 313, 25);
        btnOpenReceiptLog.addActionListener((e) ->  m_CurrentTable.makeUnavailable());
        this.add(btnOpenReceiptLog);

        JButton btnFreeTable = new JButton("Освободи маса");
        btnFreeTable.setBounds(10, 475, 313, 25);
        btnFreeTable.addActionListener((e) -> {
            m_CurrentTable.makeAvailable();
            m_PaneReceipt.setText("");
        });
        this.add(btnFreeTable);
    }

    public void setText(String text) {
        m_PaneReceipt.setText(text);
    }

    public Table getCurrentTable() { return m_CurrentTable; }

    void setCurrentTable(Table table) {
        m_CurrentTable = table;
        m_TableName.setText(table.getName());
        if (m_CurrentTable.Order != null)
            m_PaneReceipt.setText(m_CurrentTable.Order.toString());
        else
            m_PaneReceipt.setText("");
    }
}
