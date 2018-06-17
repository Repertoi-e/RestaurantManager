package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.GlobalColors;
import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.util.ResizeListener;
import edu.school.restaurantmanager.util.ResourceLoader;
import edu.school.restaurantmanager.util.Utils;
import edu.school.restaurantmanager.workfile.WorkFile;

import java.awt.*;
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

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

// Полето, където се показва менюто.

public class MenuView extends JPanel {

    public JScrollPane ParentScrollPane;
    private HashMap<String, Category> m_Categories = new HashMap<>();

	public MenuView() {
        this.setLayout(null);
		this.setBackground(GlobalColors.MENUVIEW_BG_COLOR);

		// Когато се промени размера на прозореца, подреждаме менюто
		this.addComponentListener(new ResizeListener((width, height) -> rearrangeMenu()));
	}

	public void setButtonsVisible(boolean visible) {
        for (Map.Entry entry : m_Categories.entrySet())
            ((Category) entry.getValue()).setButtonsVisible(visible);
    }

	public void updateItems(File imagesDir, String newItems) {
	    // i = 1, пропуска заглавието, тъй като и то е компонент
	    for (int i = 1; i < this.getComponentCount(); i++)
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
				File menuFile = new File("Default.restaurant");

                // Всеки MenuItem има име, цена, файл - снимка и категория.
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

        // След като са добавени новите продукти,
        // караме Java да ги нарисува.
        rearrangeMenu();

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
        this.setPreferredSize(new Dimension(x, this.getHeight()));
        ParentScrollPane.revalidate();
        ParentScrollPane.repaint();
    }
}
