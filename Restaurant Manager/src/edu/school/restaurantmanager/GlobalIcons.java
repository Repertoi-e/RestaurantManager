package edu.school.restaurantmanager;

// Клас с иконки за бутоните в програмата

import edu.school.restaurantmanager.util.Utils;
import edu.school.restaurantmanager.workfile.WorkFile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GlobalIcons {

    static final int BUTTON_SIZE = 30;

    static ImageIcon ADD_TABLE_ICON = Utils.getScaledButtonIcon(MainFrame.class.getResource("add-table.png"), BUTTON_SIZE - 9);
    static ImageIcon REMOVE_TABLE_ICON = Utils.getScaledButtonIcon(MainFrame.class.getResource("remove-table.png"), BUTTON_SIZE - 9);
    static ImageIcon EDIT_ICON = Utils.getScaledButtonIcon(WorkFile.class.getResource("edit-icon.png"), BUTTON_SIZE - 9);
    public static ImageIcon OPEN_ICON = Utils.getScaledButtonIcon(WorkFile.class.getResource("open-icon.png"), BUTTON_SIZE - 9);
}
