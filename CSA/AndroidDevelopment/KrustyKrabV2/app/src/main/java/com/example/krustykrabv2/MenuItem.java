package com.example.krustykrabv2;

public abstract class MenuItem {
    private String itemName;
    private double itemCost;
    public MenuItem(String itemName, double itemCost){
        this.itemName = itemName;
        this.itemCost = itemCost;
    }
    public String getItemName(){return itemName;}
    public double getItemCost(){return itemCost;}
}
