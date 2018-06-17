package edu.school.restaurantmanager.table;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.table.types.TableDiamond;
import edu.school.restaurantmanager.table.types.TableRectangle;
import edu.school.restaurantmanager.table.types.TableRound;
import edu.school.restaurantmanager.util.ResizeListener;

import java.awt.*;

import javax.swing.*;

// Полето, където се показват масите.

public class TableView extends JPanel {

    private TableViewOrder m_OrderFrame = new TableViewOrder();

    public final TableMover TableMover = new TableMover();
    public final TableResizer TableResizer = new TableResizer();

    private boolean m_Editing = false;
    public boolean RemovingTable = false;
	
	public TableView() {
		this.setBackground(GlobalColors.TABLEVIEW_BG_COLOR);
		this.setLayout(null);

		// При промяна на размера на прозореца, полето с масите също
        // променя размера си. Ако се сложат маси там, докато прозореца
        // е голям и после се смали, масите остават извън малкото поле
        // и са невидими.
        // Затова всеки път като се промени размера, проверяваме за маси
        // извън полето.
		this.addComponentListener(new ResizeListener((width, height) -> {
            for (int i = 1; i < this.getComponentCount(); i++)
            {
                Component table = this.getComponent(i);

                int locationX = table.getX(), locationY = table.getY();
                while (locationX + table.getWidth() + TableMover.EDGE_INSETS.right > width)
                    locationX -= TableMover.SNAP_SIZE.width;

                while (locationY + table.getHeight() + TableMover.EDGE_INSETS.bottom > height)
                    locationY -= TableMover.SNAP_SIZE.height;
                table.setLocation(locationX, locationY);
            }
        }));

        setEditing(false);

        this.add(new Table(100, 100, 180, 250, new TableRectangle()));
        this.add(new Table(300, 350, 90, 90, new TableRound()));
        this.add(new Table(300, 350, 90, 90, new TableDiamond()));
	}

    public boolean isEditing() {
        return m_Editing;
    }

    public void setEditing(boolean editing) {
        m_Editing = editing;
        if (m_Editing) {
            // Минава през всички маси
            for (int i = 0; i < this.getComponentCount(); i++) {
                Component table = this.getComponent(i);
                table.addMouseListener(TableMover);
                table.addMouseListener(TableResizer);
                table.addMouseMotionListener(TableResizer);
            }
        } else {
            for (int i = 0; i < this.getComponentCount(); i++) {
                Component table = this.getComponent(i);
                table.removeMouseListener(TableMover);
                table.removeMouseListener(TableResizer);
                table.removeMouseMotionListener(TableResizer);
            }
            RemovingTable = false;
        }
    }

    void removeTable(Table targetTable) {
        RemovingTable = false;
        MainFrame.getRemoveTableButton().setBackground(GlobalColors.MENUITEM_REMOVE_BG);
        this.remove(targetTable);

        this.invalidate();
        this.repaint();
    }

    public TableViewOrder getOrderFrame() { return m_OrderFrame; }
}
