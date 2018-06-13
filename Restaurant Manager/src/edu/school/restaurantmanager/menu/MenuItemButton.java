package edu.school.restaurantmanager.menu;

import edu.school.restaurantmanager.util.Utils;

import javax.swing.*;
import java.awt.*;

public class MenuItemButton extends JButton {

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(this.getForeground());
        Utils.drawCenteredString(g, this.getText(), new Rectangle(0, 0, this.getWidth(), this.getHeight()), this.getFont());
    }
}
