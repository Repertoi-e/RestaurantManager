package edu.school.restaurantmanager;

import java.awt.BorderLayout;
import edu.school.restaurantmanager.objects.*;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
		final int TABLE_SIZE = 78; 
		
		ArrayList<Table> tables = new ArrayList<>();
		
		for(int i = 0 ; i < 31 ; i++) {
			tables.add(new Table());
		}
		
		int currX;
		int currY;
		int f = 0;
		int g = 0;
		for (int i = 0; i < tables.size(); i++) {
			if(X+(TABLE_SIZE*g)+5 > 540) {
				g= 0;
				f++;
				continue;
			}
			tables.get(i).setId(i);
			tables.get(i).setUsing(i % 2 == 0);
			tables.get(i).getLayout().setBounds(X+(TABLE_SIZE*g)+5, Y+(TABLE_SIZE*f)+5, TABLE_SIZE, TABLE_SIZE);
			tables.get(i).getLayout().setText("Table"+tables.get(i).getId());
			tables.get(i).getLayout().setBorder(new LineBorder(tables.get(i).getUsing() ? Color.GREEN : Color.RED, 2));
			contentPane.add(tables.get(i).getLayout());
			g++;
		}
			
	}
}
