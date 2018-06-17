package edu.school.restaurantmanager.util;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ResizeListener implements ComponentListener {
    public interface ResizeCallback {
        void updateBounds(int width, int height);
    }

    private ResizeCallback m_ResizeCallback;

    public ResizeListener(ResizeCallback callback) {
        m_ResizeCallback = callback;
    }

	@Override
	public void componentHidden(ComponentEvent e)
	{ }

	@Override
	public void componentMoved(ComponentEvent e)
	{ }

	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getComponent() == null)
			return;
        m_ResizeCallback.updateBounds(e.getComponent().getWidth(), e.getComponent().getHeight());
    }

	@Override
	public void componentShown(ComponentEvent e)
	{ }
}
