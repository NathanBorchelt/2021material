package com.example.krustykrabv2;

public class MiscItem extends MenuItem implements Sauce{
    private boolean sauce;
    public MiscItem(String itemName, double itemCost){
        super(itemName,itemCost);
    }
    public MiscItem(String itemName, double itemCost, boolean sauce){
        super(itemName,itemCost);
        this.sauce = sauce;
    }

    public boolean sauce() {return sauce;}
}
