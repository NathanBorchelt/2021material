package com.example.krustykrabv2;

public class Soda extends MenuItem implements ItemSize {
    private String sizeName;
    private double upcharge;
    public Soda(double cost, String sizeName, double upcharge){
        super("Seafoam Soda", cost);
        this.sizeName = sizeName;
        this.upcharge = upcharge;
    }
    public Soda(Soda soda, String sizeName, double upcharge){
        super(soda.getItemName(),soda.getItemCost());
        this.sizeName = sizeName;
        this.upcharge = upcharge;
    }
    public Soda(double cost){
        super("Seafoam Soda", cost);
    }
    public String size() {return sizeName;}
    public double sizeCost() {return upcharge;}
}
