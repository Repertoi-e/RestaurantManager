package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.GlobalColors;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

// Полето, където се показва менюто.

public class MenuView extends JPanel {

    private LinkedHashMap<String, Category> m_Categories = new LinkedHashMap<>();

	public MenuView() {
        this.setLayout(null);
		this.setBackground(GlobalColors.MENUVIEW_BG_COLOR);
	}

	public void setButtonsVisible(boolean visible) {
        for (Map.Entry entry : m_Categories.entrySet())
            ((Category) entry.getValue()).setButtonsVisible(visible);
    }

	public void updateItems(File imagesDir, String newItems) {
        m_Categories.clear();
	    for (int i = 0; i < this.getComponentCount(); i++)
	        this.remove(i);

	    for (String currLine : newItems.split("\\r?\\n")) {
	        // Пропуска коментари.
	        if (currLine.isEmpty() || currLine.startsWith("#"))
	            continue;

	        Pattern pattern = Pattern.compile("PRODUCT:\\s*/name:(.+?(?=/price:))/price:([0-9]*)\\s*/image:(.+?(?=/cat:))/cat:(.*)");
	        Matcher matcher = pattern.matcher(currLine);
	        if (matcher.find()) {
                String name = matcher.group(1).trim(); // trim() премахва разстояния накрая на името
                int price = Integer.parseInt(matcher.group(2));
                String image = matcher.group(3).trim(); // trim() премахва разстояния накрая на името
				String category = matcher.group(4).trim();

				MenuItem item = new MenuItem(name, price, new File(imagesDir.toPath().toString() + "\\" + image), category);

                Category categoryPanel = m_Categories.get(category);
                if (categoryPanel == null) {
                    categoryPanel = new Category(this, category);
                    m_Categories.put(category, categoryPanel);
                    this.add(categoryPanel);
                }
                categoryPanel.add(item);
            }
        }

        File menuFile = new File("Menu.txt");
	    if (!menuFile.exists())
        {
            System.out.println("---------------------------------");
            System.out.println("Menu file doesn't exist: " + menuFile.toString());
            System.out.println("---------------------------------");
        }
        try {
            Files.write(menuFile.toPath(), newItems.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        rearrangeMenu();

        // След като са добавени новите продукти,
        // караме Java да ги нарисува.
        this.invalidate();
	    this.repaint();
	}

	public void rearrangeMenu() {
        int categoryPadding = 5;
        int x = categoryPadding;
        for (Map.Entry entry : m_Categories.entrySet()) {
            Category category = (Category)entry.getValue();
            category.rearrangeItems();
            category.setLocation(x, categoryPadding);
            x += category.getWidth() + categoryPadding;
        }
        this.setPreferredSize(new Dimension(x, this.getPreferredSize().height));
    }
}
