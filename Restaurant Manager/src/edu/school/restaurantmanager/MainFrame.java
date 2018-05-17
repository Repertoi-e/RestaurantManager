package edu.school.restaurantmanager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

class Table {
	private JButton layout;
	private int id;
	
	public Table() {
		layout = new JButton();
	}
	
	public JButton getLayout() {
		return layout;
	}
	
	public int getId() {
		return id;
	}
	
	public void seId(int id) {
		this.id = id;
	}
	
}

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final int X = 5;
		final int Y = 5;
		final int TABLE_SIZE = 80; 
		
		Table[] tables = {
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
				new Table(),
		};
		int currX;
		int currY;
		int f = 0;
		int g = 0;
		for (int i = 0; i < tables.length; i++) {
			if(X+TABLE_SIZE*g > 540) {
				g= 0;
				f++;
				continue;
			}
			tables[i].seId(i);
			tables[i].getLayout().setBounds(X+TABLE_SIZE*g, Y+TABLE_SIZE*f, TABLE_SIZE, TABLE_SIZE);
			tables[i].getLayout().setText("Table"+tables[i].getId());
			contentPane.add(tables[i].getLayout());
			g++;
		}
			
	}
}
