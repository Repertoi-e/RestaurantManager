package edu.school.restaurantmanager;

import edu.school.restaurantmanager.menu.MenuView;
import edu.school.restaurantmanager.table.Table;
import edu.school.restaurantmanager.table.TableShape;
import edu.school.restaurantmanager.table.types.TableDiamond;
import edu.school.restaurantmanager.table.types.TableRectangle;
import edu.school.restaurantmanager.table.types.TableRound;
import edu.school.restaurantmanager.util.Utils;
import edu.school.restaurantmanager.workfile.WorkFile;
import edu.school.restaurantmanager.table.TableView;
import edu.school.restaurantmanager.util.Fonts;

import javax.swing.*;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
	
	// Размерът на прозореца
    private static final Dimension MinimumSize = new Dimension(720, 400);
    private static final Dimension PreferredSize = new Dimension(960, 540);

    private Container m_ContentPane;
    private JPanel m_TablesHeading = new JPanel(), m_MenuHeading = new JPanel();
    private TableView m_TableView = null;
    private MenuView m_MenuView = null;
    private JButton m_RemoveTableButton;

    private WorkFile m_WorkFile = new WorkFile();

    private static final MainFrame Instance = new MainFrame();

	// "Простроява" потребителския интерфейс
	private void bakeUI() {
        GridBagConstraints gcd = new GridBagConstraints();

        Font headingFont = new Font("SourceSansPro", Font.ITALIC | Font.BOLD, 25);
        Map<TextAttribute, Object> attribs = new HashMap<>(headingFont.getAttributes());
        attribs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        headingFont = headingFont.deriveFont(attribs);

        int buttonSize = GlobalIcons.BUTTON_SIZE;
        // Заглавие при масите
        {
            m_TablesHeading.setLayout(new GridBagLayout());
            m_TablesHeading.setBackground(GlobalColors.TABLEVIEW_HEAD_COLOR);

            GridBagConstraints gcdHead = new GridBagConstraints();

            JLabel label = new JLabel("Маси");
            label.setFont(headingFont);
            label.setForeground(GlobalColors.TEXT_COLOR);
            label.setBounds(10, 0, 165, 45);

            gcdHead.fill = GridBagConstraints.BOTH;
            gcdHead.gridwidth = 19;
            gcdHead.gridheight = 1;
            gcdHead.weightx = 1.0;
            gcdHead.insets = new Insets(10, 10, 10, 0);
            m_TablesHeading.add(label, gcdHead);

            Font buttonFont = new Font("SourceSansPro", Font.BOLD, 10);

            // Бутон за добавяне на маса
            JButton addTableButton = new JButton(GlobalIcons.ADD_TABLE_ICON);
            addTableButton.setIconTextGap(addTableButton.getIconTextGap() + 5);
            addTableButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
            addTableButton.setBackground(GlobalColors.EDITTABLELAYOUT_ON);
            addTableButton.setFont(buttonFont);
            addTableButton.setFocusPainted(false);
            addTableButton.setVisible(false);
            addTableButton.addActionListener(action -> {
                int option = JOptionPane.showOptionDialog(null,
                        "Изберете форма на масата",
                        "Нова маса",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new String[] { "Правоъгълник", "Кръг", "Диамант" },
                        null);
                if (option != JOptionPane.CLOSED_OPTION) {
                    TableShape shape = null;
                    if (option == JOptionPane.YES_OPTION)
                        shape = new TableRectangle();
                    if (option == JOptionPane.NO_OPTION)
                        shape = new TableRound();
                    if (option == JOptionPane.CANCEL_OPTION)
                        shape = new TableDiamond();
                    Table table = new Table(20, 20,100, 100, shape);
                    table.addMouseListener(m_TableView.TableMover);
                    table.addMouseListener(m_TableView.TableResizer);
                    table.addMouseMotionListener(m_TableView.TableResizer);

                    m_TableView.add(table);
                    m_TableView.invalidate();
                    m_TableView.repaint();
                }
            });

            gcdHead.gridx = 22;
            gcdHead.gridwidth = 1;
            gcdHead.weightx = 0.0;
            gcdHead.insets = new Insets(10, 10, 10, 0);
            m_TablesHeading.add(addTableButton, gcdHead);

            // Бутон за махане на маса
            m_RemoveTableButton = new JButton(GlobalIcons.REMOVE_TABLE_ICON);
            m_RemoveTableButton.setIconTextGap(m_RemoveTableButton.getIconTextGap() + 5);
            m_RemoveTableButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
            m_RemoveTableButton.setBackground(GlobalColors.MENUITEM_REMOVE_BG);
            m_RemoveTableButton.setFont(buttonFont);
            m_RemoveTableButton.setFocusPainted(false);
            m_RemoveTableButton.setVisible(false);
            m_RemoveTableButton.addActionListener(action -> {
                if (m_TableView.RemovingTable) {
                    m_TableView.RemovingTable = false;
                    m_RemoveTableButton.setBackground(GlobalColors.MENUITEM_REMOVE_BG);
                }
                else {
                    m_TableView.RemovingTable = true;
                    m_RemoveTableButton.setBackground(GlobalColors.MENUITEM_REMOVE_BG.brighter().brighter());
                }
            });

            gcdHead.gridx = 21;
            gcdHead.gridwidth = 1;
            gcdHead.weightx = 0.0;
            gcdHead.insets = new Insets(10, 10, 10, 0);
            m_TablesHeading.add(m_RemoveTableButton, gcdHead);

            // Бутон за променяне на масите
            JButton editTablesButton = new JButton(GlobalIcons.EDIT_ICON);
            editTablesButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
            editTablesButton.setBackground(GlobalColors.TABLEVIEW_BG_COLOR);
            editTablesButton.setFocusPainted(false);
            editTablesButton.addActionListener(action -> {
                JButton button = (JButton)action.getSource();

                boolean editing = !m_TableView.isEditing();
                button.setBackground(editing ? GlobalColors.EDITTABLELAYOUT_ON : GlobalColors.EDITTABLELAYOUT_OFF);
                addTableButton.setVisible(editing);
                m_RemoveTableButton.setVisible(editing);
                m_TableView.setEditing(editing);
            });

            gcdHead.gridx = 23;
            gcdHead.insets = new Insets(10, 10, 10, 10);
            m_TablesHeading.add(editTablesButton, gcdHead);

            gcd.fill = GridBagConstraints.BOTH;
            gcd.gridwidth = 12;
            gcd.gridheight = 2;
            gcd.weightx = 1.0;
            m_ContentPane.add(m_TablesHeading, gcd);
        }

        // Заглавие при менюто
        {
            m_MenuHeading.setLayout(new GridBagLayout());
            m_MenuHeading.setBackground(GlobalColors.MENUVIEW_HEAD_COLOR);

            GridBagConstraints gcdHead = new GridBagConstraints();

            JLabel label = new JLabel("Mеню");
            label.setFont(headingFont);
            label.setForeground(GlobalColors.TEXT_COLOR);
            label.setBounds(10, 0, 80, 45);

            gcdHead.fill = GridBagConstraints.BOTH;
            gcdHead.gridwidth = 1;
            gcdHead.gridheight = 1;
            gcdHead.weightx = 1.0;
            gcdHead.insets = new Insets(10, 10, 10, 0);
            m_MenuHeading.add(label, gcdHead);

            // Бутон за избиране на Work file
            JButton editMenuButton = new JButton(GlobalIcons.EDIT_ICON);
            editMenuButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
            editMenuButton.setBackground(GlobalColors.TABLEVIEW_BG_COLOR);
            editMenuButton.setFocusPainted(false);
            editMenuButton.addActionListener(action -> {
                if (m_WorkFile.getImageDir() == null)
                {
                    JOptionPane.showMessageDialog(null, "Изберете директория със снимките, които ще се използват в менюто.", "Меню", JOptionPane.INFORMATION_MESSAGE);
                    if (!m_WorkFile.chooseImageDirectory())
                    {
                        JOptionPane.showMessageDialog(null, "Моля, изберете директория.", "Грешка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                m_WorkFile.setVisible(true);
            });

            gcdHead.gridx = 1;
            gcdHead.gridwidth = 1;
            gcdHead.weightx = 0.0;
            gcdHead.insets = new Insets(10, 10, 10, 10);
            m_MenuHeading.add(editMenuButton, gcdHead);

            gcd.gridx = 13;
            gcd.gridwidth = 8;
            gcd.gridheight = 2;
            gcd.weightx = 0.0;
            m_ContentPane.add(m_MenuHeading, gcd);
        }

        // Поле с маси
        {
            m_TableView = new TableView();

            gcd.gridx = 0;
            gcd.gridy = 2;
            gcd.gridwidth = 13;
            gcd.gridheight = 18;
            gcd.weightx = 1.0;
            gcd.weighty = 1.0;
            m_ContentPane.add(m_TableView, gcd);
        }

        // Меню
        {
            m_MenuView = new MenuView(450);
            m_MenuView.setPreferredSize(new Dimension(350, 0));

            gcd.gridx = 13;
            gcd.gridy = 2;
            gcd.gridwidth = 8;
            gcd.gridheight = 18;
            gcd.weightx = 0;
            gcd.weighty = 1.0;
            m_ContentPane.add(m_MenuView, gcd);
        }
	}

	private MainFrame() {
		Fonts.registerFonts();

        m_WorkFile.setVisible(false);

        this.setTitle("Ресторант");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(MainFrame.PreferredSize);
		this.setMinimumSize(MainFrame.MinimumSize);
		this.setLocationRelativeTo(getOwner());

        m_ContentPane = this.getContentPane();
		m_ContentPane.setLayout(new GridBagLayout());

        bakeUI();
    }

    public static MenuView getMenuView()
    {
        return Instance.m_MenuView;
    }
    public static TableView getTableView()
    {
        return Instance.m_TableView;
    }
    public static JButton getRemoveTableButton()
    {
        return Instance.m_RemoveTableButton;
    }

    public static void main(String[] args) {
	    Instance.setVisible(true);
	}
}
