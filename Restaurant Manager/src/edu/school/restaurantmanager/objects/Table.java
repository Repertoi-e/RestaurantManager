package edu.school.restaurantmanager.objects;

import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;

public class Table {
	private JButton layout;
	private int id;
	private boolean isUsed;
	private ArrayList<MenuProduct> orderedProducts;
	private int bill;
	
	public Table() {
		layout = new JButton();
		orderedProducts = new ArrayList<>();
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

    public List<MenuProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void addProduct(MenuProduct orderedProduct) {
        this.orderedProducts.add(orderedProduct);
    }

    public void clearProducts() {
	    this.orderedProducts = new ArrayList<>();
    }
}