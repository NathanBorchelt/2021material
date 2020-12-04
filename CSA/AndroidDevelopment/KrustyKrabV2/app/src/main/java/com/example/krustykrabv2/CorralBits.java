package com.example.krustykrabv2;

import android.widget.RadioButton;

public class CorralBits extends SizeableItem implements ItemSize {
    private String size;
    private double upcharge;
    public CorralBits(){
        super("Corral Bits",1);
    };
    public CorralBits(double itemCost,String size, double upcharge){
        super("Corral Bits",itemCost);
        this.size = size;
        this.upcharge = upcharge;
    }

    public CorralBits(CorralBits corralBits,String size, double upcharge){
        super(corralBits.getItemName(), corralBits.getItemCost());
        this.size = size;
        this.upcharge = upcharge;
    }


    public String size() {return size;}
    public double sizeCost() {return upcharge;}
    public double totalCost() {return getItemCost()+upcharge;}
}