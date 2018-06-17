package edu.school.restaurantmanager.table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import static edu.school.restaurantmanager.table.TableMover.getDragDistance;

/**
 * Код от статия:
 * https://tips4java.wordpress.com/2009/09/13/resizing-components/
 */

public class TableResizer extends MouseAdapter {

    private static HashMap<Integer, Integer> cursors = new HashMap<>();
    static {
        cursors.put(1, Cursor.N_RESIZE_CURSOR);
        cursors.put(2, Cursor.W_RESIZE_CURSOR);
        cursors.put(4, Cursor.S_RESIZE_CURSOR);
        cursors.put(8, Cursor.E_RESIZE_CURSOR);
        cursors.put(3, Cursor.NW_RESIZE_CURSOR);
        cursors.put(9, Cursor.NE_RESIZE_CURSOR);
        cursors.put(6, Cursor.SW_RESIZE_CURSOR);
        cursors.put(12, Cursor.SE_RESIZE_CURSOR);
    }

    private int m_Direction;
    private static final int NORTH = 1;
    private static final int WEST = 2;
    private static final int SOUTH = 4;
    private static final int EAST = 8;

    private boolean m_Resizing;
    private Point m_Pressed;
    private Rectangle m_Bounds;

    private Cursor m_SourceCursor;

    @Override
    public void mouseEntered(MouseEvent e)
    {
        if (!m_Resizing)
            m_SourceCursor = e.getComponent().getCursor();
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        if (!m_Resizing)
            e.getComponent().setCursor(m_SourceCursor);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (m_Direction == 0)
            return;

        m_Resizing = true;

        Component source = e.getComponent();
        SwingUtilities.convertPointToScreen(m_Pressed = e.getPoint(), source);
        m_Bounds = source.getBounds();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        m_Resizing = false;
        e.getComponent().setCursor(m_SourceCursor);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        Component component = e.getComponent();
        Point location = e.getPoint();
        m_Direction = 0;

        if (location.x < TableMover.DRAG_INSETS.left)
            m_Direction += WEST;
        if (location.x > component.getWidth() - TableMover.DRAG_INSETS.right - 1)
            m_Direction += EAST;
        if (location.y < TableMover.DRAG_INSETS.top)
            m_Direction += NORTH;
        if (location.y > component.getHeight() - TableMover.DRAG_INSETS.bottom - 1)
            m_Direction += SOUTH;

        // Мишката не е в регион за resize-ване
        if (m_Direction == 0)
            component.setCursor(m_SourceCursor);
        else {
            // Слага подходящ курсор.
            Cursor cursor = Cursor.getPredefinedCursor(cursors.get(m_Direction));
            component.setCursor( cursor );
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (!m_Resizing)
            return;

        Table table = (Table) e.getComponent();
        Dimension minSize = table.getShape().getMinimumSize(), maxSize = table.getShape().getMaximumSize();

        Point dragged = e.getPoint();
        SwingUtilities.convertPointToScreen(dragged, table);

        int x = m_Bounds.x;
        int y = m_Bounds.y;
        int width = m_Bounds.width;
        int height = m_Bounds.height;

        // Западната и северната страна променят размера и позицията
        if (WEST == (m_Direction & WEST))
        {
            int drag = getDragDistance(m_Pressed.x, dragged.x, TableMover.SNAP_SIZE.width);
            int maximum = Math.min(width + x, maxSize.width);
            drag = getDragBounded(drag, TableMover.SNAP_SIZE.width, width, minSize.width, maximum);

            x -= drag;
            width += drag;
        }

        if (NORTH == (m_Direction & NORTH))
        {
            int drag = getDragDistance(m_Pressed.y, dragged.y, TableMover.SNAP_SIZE.height);
            int maximum = Math.min(height + y, maxSize.height);
            drag = getDragBounded(drag, TableMover.SNAP_SIZE.height, height, minSize.height, maximum);

            y -= drag;
            height += drag;
        }

        // Източната и южната страна променят само размера
        if (EAST == (m_Direction & EAST))
        {
            int drag = getDragDistance(dragged.x, m_Pressed.x, TableMover.SNAP_SIZE.width);
            Dimension boundingSize = table.getParent().getSize();
            int maximum = Math.min(boundingSize.width - x, maxSize.width);
            drag = getDragBounded(drag, TableMover.SNAP_SIZE.width, width, minSize.width, maximum);
            width += drag;
        }

        if (SOUTH == (m_Direction & SOUTH))
        {
            int drag = getDragDistance(dragged.y, m_Pressed.y, TableMover.SNAP_SIZE.height);
            Dimension boundingSize = table.getParent().getSize();
            int maximum = Math.min(boundingSize.height - y, maxSize.height);
            drag = getDragBounded(drag, TableMover.SNAP_SIZE.height, height, minSize.height, maximum);
            height += drag;
        }

        if (table.getShape().shouldAlwaysBeSquare())
            width = height;

        table.setBounds(x, y, width, height);
        table.updateBounds();
        table.validate();
    }

    private int getDragBounded(int drag, int snapSize, int dimension, int minimum, int maximum)
    {
        while (dimension + drag < minimum)
            drag += snapSize;

        while (dimension + drag > maximum)
            drag -= snapSize;

        return drag;
    }
}
