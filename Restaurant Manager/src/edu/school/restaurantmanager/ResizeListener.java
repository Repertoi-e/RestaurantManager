package edu.school.restaurantmanager;

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
		MainFrame.Instance.RebuildUI();
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
	}

}
