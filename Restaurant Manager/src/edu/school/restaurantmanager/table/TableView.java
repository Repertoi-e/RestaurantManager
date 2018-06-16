package edu.school.restaurantmanager.table;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.util.Utils;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

// Полето, където се показват масите.

public class TableView extends JPanel {

    private JPanel m_Heading;
    private TableViewOrder m_OrderPanel;

    private final TableMover m_TableMover = new TableMover();
    private final TableResizer m_TableResizer = new TableResizer();

    private boolean m_Editing = false;
	
	public TableView() {
		this.setBackground(GlobalColors.TABLEVIEW_BG_COLOR);
		this.setLayout(null);

        m_Heading = new JPanel();
        m_Heading.setBackground(GlobalColors.TABLEVIEW_HEAD_COLOR);
        m_Heading.setLayout(null);
        {
            Font font = new Font("SourceSansPro", Font.ITALIC | Font.BOLD, 25);
            Map<TextAttribute, Object> fontAttribs = new HashMap<>(font.getAttributes());
            fontAttribs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

            JLabel label = new JLabel("Маси");
            label.setFont(font.deriveFont(fontAttribs));
            label.setForeground(GlobalColors.TEXT_COLOR);
            m_Heading.add(label);
        }
        this.add(m_Heading);

	    this.add(m_OrderPanel = new TableViewOrder());
		
		// Пример за жълта маса
		TableColorPalette yellow = new TableColorPalette();
		{
			yellow.Top = Color.decode("#fdd835");
			yellow.Chair = Color.decode("#fbc02d");
			yellow.ChairShadow = yellow.Chair.darker(); //Color.decode("#e8b228");
		}

        setEditing(false);

        this.add(new Table(100, 100, 180, 250, yellow));
        this.add(new Table(300, 350, 90, 90, yellow));
	}

	public void updateBounds(int windowWidth, int windowHeight) {
        // Вместо hard-code-нат размер, взимаме процент от размера на целия прозорец.
        int width = Utils.percent(windowWidth, 60);

        setBounds(0, 0, width, windowHeight);
        m_Heading.setBounds(0, 0, width, 40);
        m_Heading.getComponent(0).setBounds(10, 0, width, 40);
    }

    public boolean isEditing()
    {
        return m_Editing;
    }

    public void setEditing(boolean editing)
    {
        m_Editing = editing;
        if (m_Editing) {
            // Минава през всички маси
            for (int i = 2; i < this.getComponentCount(); i++) {
                Component table = this.getComponent(i);
                table.addMouseListener(m_TableMover);
                table.addMouseListener(m_TableResizer);
                table.addMouseMotionListener(m_TableResizer);
            }
            MainFrame.getEditTableLayoutButton().setBackground(GlobalColors.EDITTABLELAYOUT_ON);
            MainFrame.getAddTableButton().setVisible(true);
        } else {
            for (int i = 2; i < this.getComponentCount(); i++) {
                Component table = this.getComponent(i);
                table.removeMouseListener(m_TableMover);
                table.removeMouseListener(m_TableResizer);
                table.removeMouseMotionListener(m_TableResizer);
            }
            MainFrame.getEditTableLayoutButton().setBackground(GlobalColors.EDITTABLELAYOUT_OFF);
            MainFrame.getAddTableButton().setVisible(false);
        }
    }

    public TableViewOrder getOrderView() { return m_OrderPanel; }
}
