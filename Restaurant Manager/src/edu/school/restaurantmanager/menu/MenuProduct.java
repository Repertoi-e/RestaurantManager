package edu.school.restaurantmanager.menu;

//p_ == product
@SuppressWarnings("WeakerAccess")
public class MenuProduct {
    String p_Name;
    double p_Price;
    String p_Type;
    double p_Size;

    public MenuProduct(String p_Name, double p_Price, String p_Type, double p_Size) {
        this.p_Name = p_Name;
        this.p_Price = p_Price;
        this.p_Type = p_Type;
        this.p_Size = p_Size;
    }
}
