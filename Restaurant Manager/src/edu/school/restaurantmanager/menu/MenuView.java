package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.util.ResourceLoader;
import edu.school.restaurantmanager.util.Utils;
import edu.school.restaurantmanager.workfile.WorkFile;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;

// Полето, където се показва менюто.

public class MenuView extends JPanel {

    private int maxHeight;

	public MenuView(int maxHeight) {
		this.setBackground(GlobalColors.MENUVIEW_BG_COLOR);
		this.setLayout(null);
        this.maxHeight = maxHeight;
	}

	public void updateItems(File imagesDir, String newItems) {
	    // i = 1, пропуска заглавието, тъй като и то е компонент
	    for (int i = 1; i < this.getComponentCount(); i++)
	        this.remove(i);

	    int x = 10;
	    int y = 10;

	    int yMover = 0;
	    int xMover = 0;
	    int currX = x;
	    int currY = y;

	    for (String currLine : newItems.split("\\r?\\n")) {
	        // Пропуска коментари.
	        if (currLine.isEmpty() || currLine.startsWith("#"))
	            continue;

	        Pattern pattern = Pattern.compile("PRODUCT:\\s*/name:(.+?(?=/price:))/price:([0-9]*)\\s*/image:(.*)\\s*/cat:(.*)");
	        Matcher matcher = pattern.matcher(currLine);
	        if (matcher.find()) {
                String name = matcher.group(1).trim(); // trim() премахва разстояния накрая на името
                int price = Integer.parseInt(matcher.group(2));
                String image = matcher.group(3).trim(); // trim() премахва разстояния накрая на името
				String category = matcher.group(4).trim();
				File menuFile = new File("Default.restaurant");

                // Всеки MenuItem има име, цена и файл - снимка.
                MenuItem item = new MenuItem(name, price, new File(imagesDir.toPath().toString() + "\\" + image), category);
                // Тук е мястото и размера в менюто
                item.setBounds(currX, currY, 135, 135);
                // След setBounds, задължително updateBounds !!
                item.updateBounds();
                // MenuItem extend-ва JPanel
                this.add(item);
                yMover++;
                currY = y + 140 * yMover;
                if(currY >= maxHeight - 100) {
                    xMover++;
                    currX = x + 140 * xMover;
                    yMover = 0;
                    currY = y + 140 * yMover;
                }
            }
        }

        // След като са добавени новите продукти,
        // караме Java да ги нарисува.
        this.invalidate();
	    this.repaint();
	}
}
