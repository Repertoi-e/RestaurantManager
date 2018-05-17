package edu.school.restaurantmanager.objects;

import javax.swing.JButton;

public class Table {
	private JButton layout;
	private int id;
	private boolean isUsed;
	
	public Table() {
		layout = new JButton();
	}
	
	public JButton getLayout() {
		return layout;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getUsing(){
		return isUsed;
	}
	
	public void setUsing(boolean using) {
		this.isUsed = using;
	}
	
}