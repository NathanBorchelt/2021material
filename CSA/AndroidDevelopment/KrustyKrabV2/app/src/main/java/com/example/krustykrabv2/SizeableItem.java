package com.example.krustykrabv2;

import android.widget.RadioButton;

public class SizeableItem extends MenuItem implements ItemSize {
    private String size;
    private double upcharge;
    public SizeableItem(String name, double cost){super(name,cost);};

    public SizeableItem(SizeableItem sizeableItem,String size, double upcharge){
        super(sizeableItem.getItemName(), sizeableItem.getItemCost());
        this.size = size;
        this.upcharge = upcharge;
    }

    public SizeableItem(String name, double cost,String size, double upcharge){
        super(name,cost);
        this.size = size;
        this.upcharge = upcharge;
    }

    public String size() {return size;}
    public double sizeCost() {return upcharge;}
    public double totalCost() {return getItemCost()+upcharge;}
}
