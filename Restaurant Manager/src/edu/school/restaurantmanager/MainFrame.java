package edu.school.restaurantmanager;

import edu.school.restaurantmanager.frames.TableViewFrame;
import edu.school.restaurantmanager.objects.*;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.util.ArrayList;

import java.awt.Color;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(() -> {
			try {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
			if(X+(TABLE_SIZE*currX)+5 > 540) {
				currX= 0;
				currY++;
				continue;
			}
            Table currentTable = tables.get(i);
			currentTable.setId(i);
			currentTable.setUsing(i % 2 == 0);
			currentTable.getLayout().setBounds(X+(TABLE_SIZE*currX)+5, Y+(TABLE_SIZE*currY)+5, TABLE_SIZE, TABLE_SIZE);
			currentTable.getLayout().setText("Table"+tables.get(i).getId());
			currentTable.getLayout().setBorder(new LineBorder(tables.get(i).getUsing() ? Color.GREEN : Color.RED, 1));
			currentTable.getLayout().addActionListener(e -> {


			    if (currentTable.getUsing()){
                    TableViewFrame frame = new TableViewFrame(currentTable);
                    frame.setVisible(true);
                }else{
			        int messgage = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to start new bill for the table", "Are you sure", JOptionPane.OK_CANCEL_OPTION);
                    if(messgage == 0){
                        TableViewFrame frame = new TableViewFrame(currentTable);
                        frame.setVisible(true);
                    }
                }

            });
			contentPane.add(tables.get(i).getLayout());

			currX++;
		}
			
	}
}
