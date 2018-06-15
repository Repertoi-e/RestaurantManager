package edu.school.restaurantmanager.util;

import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.workfile.WorkFile;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ResizeListener implements ComponentListener {
	
	@Override
	public void componentHidden(ComponentEvent e)
	{
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
		if (e.getComponent() == null || !(e.getComponent() instanceof MainFrame))
			return;

		((MainFrame)e.getComponent()).rebuildUI();
    }

	@Override
	public void componentShown(ComponentEvent e)
	{
	}
}
