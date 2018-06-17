package edu.school.restaurantmanager.table;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Код от статия:
 * https://tips4java.wordpress.com/2009/06/14/moving-windows/
 */

public class TableMover extends MouseAdapter
{
    private Point m_Location, m_Pressed;
    private boolean m_PotentialDrag;
    private Cursor m_OriginalCursor;
    private Component m_Component;

    // Позицията на маса трябва да се дели на: ("snap" effect)
    static final Dimension SNAP_SIZE = new Dimension(10, 10);

    // Границата между местене и променяне на размера
    static final Insets DRAG_INSETS = new Insets(5, 5, 5, 5);

    // На колко пиксела може да макс разстояние от стените на полето с масите
    static final Insets EDGE_INSETS = new Insets(5, 5, 5, 5);

    @Override
    public void mousePressed(MouseEvent e) {
        m_Component = e.getComponent();
        int width  = m_Component.getWidth() - DRAG_INSETS.left - DRAG_INSETS.right;
        int height = m_Component.getHeight() - DRAG_INSETS.top - DRAG_INSETS.bottom;
        Rectangle r = new Rectangle(DRAG_INSETS.left, DRAG_INSETS.top, width, height);

        if (r.contains(e.getPoint()))
        {
            m_Component.addMouseMotionListener( this);
            m_PotentialDrag = true;

            m_Pressed = e.getLocationOnScreen();
            m_Location = m_Component.getLocation();

            m_OriginalCursor = m_Component.getCursor();
            m_Component.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Component component = e.getComponent();
        Point dragged = e.getLocationOnScreen();
        int dragX = getDragDistance(dragged.x, m_Pressed.x, SNAP_SIZE.width);
        int dragY = getDragDistance(dragged.y, m_Pressed.y, SNAP_SIZE.height);

        int locationX = m_Location.x + dragX;
        int locationY = m_Location.y + dragY;

        // Премества масата ако излиза от полето.
        while (locationX < EDGE_INSETS.left)
            locationX += SNAP_SIZE.width;

        while (locationY < EDGE_INSETS.top)
            locationY += SNAP_SIZE.height;

        Dimension d = component.getParent().getSize();

        while (locationX + component.getWidth() + EDGE_INSETS.right > d.width)
            locationX -= SNAP_SIZE.width;

        while (locationY + component.getHeight() + EDGE_INSETS.bottom > d.height)
            locationY -= SNAP_SIZE.height;

        component.setLocation(locationX, locationY);
    }

    static int getDragDistance(int larger, int smaller, int snapSize) {
        int halfway = snapSize / 2;
        int drag = larger - smaller;
        drag += (drag < 0) ? -halfway : halfway;
        drag = (drag / snapSize) * snapSize;

        return drag;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (!m_PotentialDrag)
            return;

        m_Component.removeMouseMotionListener(this);
        m_PotentialDrag = false;

        m_Component.setCursor(m_OriginalCursor);
    }
}
