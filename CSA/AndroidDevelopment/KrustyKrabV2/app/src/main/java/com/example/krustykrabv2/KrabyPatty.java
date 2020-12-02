package com.example.krustykrabv2;

public class KrabyPatty extends MenuItem implements ItemSize, Cheese{
    private String itemSize = null;
    private double itemUpCharge = 0.;
    private boolean cheese = false;
    public KrabyPatty(String itemName, double itemCost){
        super(itemName,itemCost);
    }
    public KrabyPatty(String itemName, double itemCost,String itemSize, double itemUpCharge){
        super(itemName,itemCost);
        this.itemSize = itemSize;
        this.itemUpCharge = itemUpCharge;
    }
    public KrabyPatty(String itemName, double itemCost,boolean cheese, String itemSize, double itemUpCharge){
        super(itemName,itemCost);
        this.cheese = cheese;
        this.itemSize = itemSize;
        this.itemUpCharge = itemUpCharge;
    }
    public String size() {return itemSize;}
    public double sizeCost() {return itemUpCharge;}
    public boolean cheese() { return cheese;}
}
