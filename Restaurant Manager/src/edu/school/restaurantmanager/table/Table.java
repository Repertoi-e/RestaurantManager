package edu.school.restaurantmanager.table;

import java.awt.*;
import java.time.LocalTime;

import javax.swing.JButton;

import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.table.order.ReceiptLog;
import edu.school.restaurantmanager.table.types.TableRectangle;
import edu.school.restaurantmanager.util.Utils;

// Всяка маса е бутон с различен изглед.

public class Table extends JButton {

    public ReceiptLog Order = null;

    String m_Name;
    private TableShape m_Shape;
	private TableGraphics m_Graphics = new TableGraphics();

	private static int s_TableCounter = 0;

	/*
	 * x, y - мястото на масата в полето
	 * width, height - големината на масата
	 * shape - формата
	 */
	public Table(int x, int y, int width, int height, TableShape shape) {
		this.setBounds(x, y, width, height);
		this.setContentAreaFilled(false);

		m_Name = "#" + ++ /*лол*/ s_TableCounter;
        m_Shape = shape;

        updateBounds();

        this.addActionListener((e) -> {
            TableView tableView = MainFrame.getTableView();
            if (tableView.isEditing())
            {
                if (tableView.RemovingTable)
                    tableView.removeTable(this);
            }
            else
            {
                tableView.getOrderFrame().setVisible(true);
                tableView.getOrderFrame().setCurrentTable(this);
            }
        });
	}

	TableShape getShape() { return m_Shape; }

	// FIXME
	void updateBounds() {
	    int width = this.getWidth();
	    int height = this.getHeight();

        m_Graphics.updateBounds(width / 2, height / 2);
    }

    void makeUnavailable() {
        Order = new ReceiptLog();

        this.invalidate();
        this.repaint();
    }

    void makeAvailable() {
        Order = null;

        this.invalidate();
        this.repaint();
    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.translate(20, 20);
		Dimension tableDimension = new Dimension(this.getWidth() - 20 * 2, this.getHeight() - 20 * 2);
		m_Shape.draw(tableDimension, Order == null ? TableColorPalette.AVAILABLE : TableColorPalette.UNAVAILABLE, (Graphics2D) g);
        g.translate(-20, -20);

        m_Graphics.draw((Graphics2D) g, this);
	}
}
