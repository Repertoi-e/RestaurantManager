package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.util.ResourceLoader;
import edu.school.restaurantmanager.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.*;
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

	public static final Color BACKGROUND_COLOR = Color.decode("#718792");
    public static final Color HEADING_COLOR = BACKGROUND_COLOR.darker();
    private int maxHeight;
	JPanel m_Heading;

	public MenuView(int maxHeight) {
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(null);
		this.maxHeight = maxHeight;
		m_Heading = new JPanel();
		m_Heading.setBackground(HEADING_COLOR);
		m_Heading.setLayout(null);
		{
			Font font = new Font("SourceSansPro", Font.ITALIC | Font.BOLD, 25);
			Map<TextAttribute, Object> fontAttribs = new HashMap<>(font.getAttributes());
			fontAttribs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

			JLabel label = new JLabel("Mеню");
			label.setBounds(10, 10, 100, 25);
			label.setFont(font.deriveFont(fontAttribs));
			label.setForeground(Color.decode("#eeeeee"));
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

	private void addItem(String name, int price, URL url) throws Exception {
		File f = new File(ResourceLoader.getResource("/res/menu_items.txt").getFile());
		FileWriter fw = new FileWriter(f,true);
		BufferedWriter writer = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(fw);
		out.println(String.format("%s-%d-%s", name, price, String.valueOf(url)));
		out.close();
	}

	public void showItems() {
		try {
            File f = MainFrame.getWorkFile().getFile();
            FileReader fr = new FileReader(f);
            BufferedReader reader = new BufferedReader(fr);

            String currLine;
            int yMover = 0;
            int xMover = 0;
            int currX = 10;
            int currY = 50;
            System.out.println("--------------------Loading file:");
            while ((currLine = reader.readLine()) != null){
                Pattern pattern = Pattern.compile("PRODUCT:\\s*/name:(.+?(?=/price:))/price:([0-9]*)\\s*/file:(.*)");
                Matcher matcher = pattern.matcher(currLine);
                if (matcher.find()) {
                    String name = matcher.group(1).trim(); // trim() премахва разстояния накрая на името
                    int price = Integer.parseInt(matcher.group(2));
                    String image = matcher.group(3).trim();

                    System.out.println("Name: \"" + name + "\"" + ", Price: " + price + ", Image: \"" + image + "\"");
                }
                //ArrayList<String> line = Arrays.stream(currLine.split("-")).collect(Collectors.toCollection(ArrayList::new));
//
                //// Всеки MenuItem има име, цена и URL с снимка.
                //MenuItem item = new MenuItem(line.get(0), Integer.parseInt(line.get(1)), new URL(line.get(2)));
                //// Тук е мястото и размера в менюто
                //item.setBounds(currX, currY, 135, 135);
                //// След setBounds, задължително updateBounds !!
                //item.updateBounds();
                //// MenuItem extend-ва JPanel
                //this.add(item);
                //yMover++;
                //currY = y + 135 * yMover;
                //if(currY >= maxHeight - 100){
                //	xMover++;
                //	currX = x + 135 * xMover;
                //	yMover = 0;
                //	currY = y + 135 * yMover;
                //}
            }
        } catch (Exception e ) { e.printStackTrace(); }
	}
}
