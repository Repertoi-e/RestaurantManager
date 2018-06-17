package edu.school.restaurantmanager.table.order;

import edu.school.restaurantmanager.MainFrame;
import edu.school.restaurantmanager.util.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptLog
{
    // Списък с редове в касовата бележка.
    private List<ReceiptEntry> m_ReceiptEntries = new ArrayList<>();
    private int m_Total = 0;

    // Добавя нов вид продукт в касовата бележка.
    // Ако вече го има, добавя само брой
    public boolean add(ReceiptEntry entry) {
        if (!m_ReceiptEntries.contains(entry))
        {
            ReceiptEntry addEntry = new ReceiptEntry(entry);
            m_ReceiptEntries.add(addEntry);
            if (addEntry.m_Count < 0)
            {
                addEntry.m_Count = 0;
                return false;
            }
        }
        else
        {
            ReceiptEntry existingEntry = m_ReceiptEntries.get(m_ReceiptEntries.indexOf(entry));
            existingEntry.m_Count += entry.m_Count;
            if (existingEntry.m_Count < 0)
            {
                existingEntry.m_Count = 0;
                return false;
            }
        }
        updateTotalPrice();
        MainFrame.getTableView().invalidate();
        MainFrame.getTableView().repaint();
        MainFrame.getTableView().getOrderFrame().setText(toString());
        return true;
    }

    private void updateTotalPrice()
    {
        m_Total = 0;
        for (ReceiptEntry m_ReceiptEntry : m_ReceiptEntries)
            m_Total += m_ReceiptEntry.getPrice();
    }

    @Override
    public String toString()
    {
        String entries = m_ReceiptEntries.stream().filter(e -> e.m_Count != 0).map(e -> "<pre>" + e + "</pre>\n").collect(Collectors.joining());
        if (entries.equals(""))
            return "";

        String total = "<pre><b>" + String.format("      Общо:   %23s", Utils.getPriceAsString(m_Total)) + "</b></pre>";

        final String html = "<!DOCTYPE html><head><meta charset=\"UTF-8\">"
                + "<div style=\"margin:0;"
                + "			    padding:0;"
                + "				border:0;"
                + "             font-family:Consolas;"
                + "				font-size:10px\">"
                + "     <i><pre style=\"font-size:9px\">  924YU07TF1S2UIAW2812FFACMN127GCAIJSO3C</pre></i>\n\n"
                + "		<pre>     Продукт.                   Цена.</pre>\n"
                + "		<pre>#-----------------------------------#</pre>\n"
                + "     %s"
                + "     <pre>#-----------------------------------#</pre>\n"
                + "     %s"
                + "		<div style=\"font-size: 15px\"><b><pre>\n\n      ФИКСАЛЕН БОН</pre></b></div>"
                + "		<div style=\"font-size: 11px\"><b><pre>                            0004184</pre></b></div>"
                + "</div></head></html>";

        return String.format(html, entries, total);
    }

    public int getTotal() {
        return m_Total;
    }
}
