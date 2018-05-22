package edu.school.restaurantmanager.objects;

import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;

public class Table {
	public int id;
	public boolean isUsed;
	
	JButton m_Layout;
	ArrayList<MenuProduct> m_OrderedProducts;
	int bill;
	
	public Table() {
		m_Layout = new JButton();
		m_OrderedProducts = new ArrayList<>();
	}
	
	public JButton getLayout() {
		return m_Layout;
	}

    public List<MenuProduct> getOrderedProducts() {
        return m_OrderedProducts;
    }

    public void addProduct(MenuProduct orderedProduct) {
        m_OrderedProducts.add(orderedProduct);
    }

    public void clearProducts() {
	    m_OrderedProducts.clear();
    }
}