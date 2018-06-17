package edu.school.restaurantmanager.table.order;

import edu.school.restaurantmanager.menu.MenuItem;
import edu.school.restaurantmanager.util.Utils;

public class ReceiptEntry
{
    private MenuItem m_Product;
    int m_Count;

    public ReceiptEntry(MenuItem product, int count)
    {
        m_Product = product;
        m_Count = count;
    }

    // Copy constructor
    ReceiptEntry(ReceiptEntry other)
    {
        m_Product = other.m_Product;
        m_Count = other.m_Count;
    }

    // Дава цената умножена по броя от поръчания продукт
    int getPrice()
    {
        return m_Count * m_Product.getPrice();
    }

    @Override
    public String toString()
    {
        String count = String.format("%3d x  %s", m_Count, Utils.getPriceAsString(m_Product.getPrice(), false));
        String price = Utils.getPriceAsString(getPrice(), true);

        return String.format(" %-23s\n   %-5s %21s", m_Product.getName(), count, price);
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof ReceiptEntry)
            return ((ReceiptEntry) other).m_Product.equals(m_Product);
        return false;
    }
}
