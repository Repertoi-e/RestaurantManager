package edu.school.restaurantmanager.table;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.menu.MenuItem;
import edu.school.restaurantmanager.menu.MenuView;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TableViewOrder extends JFrame {

    private Table m_CurrentTable;
    private JEditorPane m_PaneReceipt = new JEditorPane();

    TableViewOrder() {
        this.setLayout(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(283, 545);
        this.setResizable(false);
        this.setBackground(GlobalColors.TABLEVIEW_BG_COLOR.brighter());
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                MenuView view = MainFrame.getMenuView();
                for (int i = 0; i < view.getComponentCount(); i++)
                    ((MenuItem)view.getComponent(i)).setButtonsVisible(false);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                MenuView view = MainFrame.getMenuView();
                for (int i = 0; i < view.getComponentCount(); i++)
                    ((MenuItem)view.getComponent(i)).setButtonsVisible(true);
            }
        });

        m_PaneReceipt.setContentType("text/html");
        m_PaneReceipt.setFont(m_PaneReceipt.getFont().deriveFont(12));

        JScrollPane scrollPane = new JScrollPane(m_PaneReceipt);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 10, 258, 422);
        this.add(scrollPane);

        JButton btnOpenReceiptLog = new JButton("Отвори сметка");
        btnOpenReceiptLog.setBounds(10, 440, 258, 25);
        btnOpenReceiptLog.addActionListener((e) ->  m_CurrentTable.makeUnavailable());
        this.add(btnOpenReceiptLog);

        JButton btnFreeTable = new JButton("Освободи маса");
        btnFreeTable.setBounds(10, 475, 258, 25);
        btnFreeTable.addActionListener((e) -> {
            m_CurrentTable.makeAvailable();
            m_PaneReceipt.setText("c");
        });
        this.add(btnFreeTable);
    }

    public void setText(String text) {
        m_PaneReceipt.setText(text);
    }

    public Table getCurrentTable() { return m_CurrentTable; }

    public void setCurrentTable(Table table) {
        m_CurrentTable = table;
        if (m_CurrentTable.Order != null)
            m_PaneReceipt.setText(m_CurrentTable.Order.toString());
        else
            m_PaneReceipt.setText("");
    }
}
