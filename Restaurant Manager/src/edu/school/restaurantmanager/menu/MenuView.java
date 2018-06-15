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
	JPanel m_Heading;

	public MenuView(int maxHeight) {
		this.setBackground(GlobalColors.MENUVIEW_BG_COLOR);
		this.setLayout(null);
		this.maxHeight = maxHeight;
		m_Heading = new JPanel();
		m_Heading.setBackground(GlobalColors.MENUVIEW_HEAD_COLOR);
		m_Heading.setLayout(null);
		{
			Font font = new Font("SourceSansPro", Font.ITALIC | Font.BOLD, 25);
			Map<TextAttribute, Object> fontAttribs = new HashMap<>(font.getAttributes());
			fontAttribs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

			JLabel label = new JLabel("Mеню");
			label.setBounds(10, 10, 100, 25);
			label.setFont(font.deriveFont(fontAttribs));
			label.setForeground(GlobalColors.TEXT_COLOR);
			m_Heading.add(label);
		}
		this.add(m_Heading);
	}

	public void updateBounds(int windowWidth, int windowHeight)
    {
        int width = Utils.percent(windowWidth, 40);
        int height = windowHeight;

        this.setBounds(windowWidth - width, 0, width, height);
        m_Heading.setBounds(0, 0, width, 40);
    }

	public void updateItems(File workFileContext, String newItems) {
	    int x = 10;
	    int y = 50;

	    int yMover = 0;
	    int xMover = 0;
	    int currX = 10;
	    int currY = 50;

	    for (String currLine : newItems.split("\\r?\\n")) {
	        // Пропуска коментари.
	        if (currLine.isEmpty() || currLine.startsWith("#"))
	            continue;

	        Pattern pattern = Pattern.compile("PRODUCT:\\s*/name:(.+?(?=/price:))/price:([0-9]*)\\s*/image:(.*)");
	        Matcher matcher = pattern.matcher(currLine);
	        if (matcher.find()) {
                String name = matcher.group(1).trim(); // trim() премахва разстояния накрая на името
                int price = Integer.parseInt(matcher.group(2));
                String image = matcher.group(3).trim(); // trim() премахва разстояния накрая на името

                // Всеки MenuItem има име, цена и файл - снимка.
                MenuItem item = new MenuItem(name, price, new File(workFileContext.getParentFile().toPath().resolve("images").toString() + "\\" + image));
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
