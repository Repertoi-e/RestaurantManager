package edu.school.restaurantmanager.table;

import javax.swing.*;
import java.awt.*;

public class TableViewOrder extends JPanel {

    public static final Color BACKGROUND_COLOR = Color.decode("#455a64");

    TableViewOrder()
    {
        this.setLayout(null);
        this.setBackground(BACKGROUND_COLOR.brighter()); // TEMP, replace with better color later.
    }
}
