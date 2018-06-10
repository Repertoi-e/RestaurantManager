package edu.school.restaurantmanager.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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



		try {
			showItems(10,50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addItem(String name, int price, URL url) throws Exception {
		File f = new File("Restaurant Manager\\src\\edu\\school\\restaurantmanager\\menu\\menu_items.txt");
		FileWriter fw = new FileWriter(f,true);
		BufferedWriter writer = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(fw);
		out.println(String.format("%s-%d-%s", name, price, String.valueOf(url)));
		out.close();
	}

	private void showItems(int x, int y) throws Exception {
		File f = new File("Restaurant Manager\\src\\edu\\school\\restaurantmanager\\menu\\menu_items.txt");
		FileReader fr = new FileReader(f);
		BufferedReader reader = new BufferedReader(fr);

		String currLine;
		int yMover = 0;
		int xMover = 0;
		int currX = x;
		int currY = y;
		while ((currLine = reader.readLine()) != null){
			ArrayList<String> line = Arrays.stream(currLine.split("-")).collect(Collectors.toCollection(ArrayList::new));

			// Всеки MenuItem има име, цена и URL с снимка.
			MenuItem item = new MenuItem(line.get(0), Integer.parseInt(line.get(1)), new URL(line.get(2)));
			// Тук е мястото и размера в менюто
			item.setBounds(currX, currY, 135, 135);
			// След setBounds, задължително updateBounds !!
			item.updateBounds();
			// MenuItem extend-ва JPanel
			this.add(item);
			yMover++;
			currY = y+135*yMover;
			if(currY >= maxHeight-100){
				xMover++;
				currX = x+ 135*xMover;
				yMover = 0;
				currY = y+135*yMover;
			}
		}
	}

	public JPanel getHeading() { return m_Heading; }
}
