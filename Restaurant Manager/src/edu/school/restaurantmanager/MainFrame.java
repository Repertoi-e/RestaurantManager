package edu.school.restaurantmanager;

import edu.school.restaurantmanager.frames.TableViewFrame;
import edu.school.restaurantmanager.objects.*;

import javax.swing.*;
import javax.swing.border.*;

import java.util.ArrayList;

import java.awt.Color;

public class MainFrame extends JFrame {
	static final long serialVersionUID = 3354660810234031086L;
	
	JPanel m_ContentPane;

	// Default constructor
	public MainFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 588, 377);
		
		m_ContentPane = new JPanel();
		m_ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		m_ContentPane.setLayout(null);
		this.setContentPane(m_ContentPane);
		
		final int X = 5;
		final int Y = 5;
		final int TABLE_SIZE = 78; 
		
		ArrayList<Table> tables = new ArrayList<>();
		
		for(int i = 0 ; i < 31 ; i++) {
			tables.add(new Table());
		}
		
		int currX = 0;
		int currY = 0;
		for (int i = 0; i < tables.size(); i++) {
			if(X + (TABLE_SIZE * currX) + 5 > 540) {
				currX= 0;
				currY++;
				continue;
			}
			
            Table currentTable = tables.get(i);
			currentTable.id = i;
			currentTable.isUsed = i % 2 == 0;
			currentTable.getLayout().setBounds(X+(TABLE_SIZE*currX)+5, Y+(TABLE_SIZE*currY)+5, TABLE_SIZE, TABLE_SIZE);
			currentTable.getLayout().setText("Table"+tables.get(i).id);
			currentTable.getLayout().setBorder(new LineBorder(tables.get(i).isUsed ? Color.GREEN : Color.RED, 1));
			currentTable.getLayout().addActionListener(e -> {
			    if (currentTable.isUsed) {
                    TableViewFrame frame = new TableViewFrame(currentTable);
                    frame.setVisible(true);
                } else {
			        int messgage = JOptionPane.showConfirmDialog(m_ContentPane, "Are you sure you want to start new bill for the table", "Are you sure", JOptionPane.OK_CANCEL_OPTION);
                    if(messgage == 0){
                        TableViewFrame frame = new TableViewFrame(currentTable);
                        frame.setVisible(true);
                    }
                }

            });
			m_ContentPane.add(tables.get(i).getLayout());

			currX++;
		}
	}
	
	// Launch the application
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new MainFrame().setVisible(true);
	}
}
