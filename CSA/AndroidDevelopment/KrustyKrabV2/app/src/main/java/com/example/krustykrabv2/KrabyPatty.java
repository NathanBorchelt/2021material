package com.example.krustykrabv2;

public class KrabyPatty extends MenuItem implements Cheese{
    private double cheeseUpcharge = 0;
    private double comboUpcharge = 0;
    private boolean cheese = false;
    public KrabyPatty(String itemName, double itemCost){
        super(itemName,itemCost);
    }
    public KrabyPatty(String itemName, double itemCost,String itemSize, double itemUpCharge){
        super(itemName,itemCost);
    }
    public KrabyPatty(String itemName, double itemCost,boolean cheese){
        super(itemName,itemCost);
        this.cheese = cheese;
        this.cheeseUpcharge = cheeseUpcharge;
    }
    public KrabyPatty(KrabyPatty makingCombo, boolean cheese,double cheeseUpcharge, double comboUpcharge){
        super(makingCombo.getItemName(),makingCombo.getItemCost());
        this.cheese = cheese;
        this.cheeseUpcharge = cheeseUpcharge;
        this.comboUpcharge=comboUpcharge;
    }

    public boolean cheese() { return cheese;}
    public double totalCost(){return getItemCost()+cheeseUpcharge+comboUpcharge;}
}
