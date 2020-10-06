package com.example.krustkrabapp;
import java.util.ArrayList;


public class Combo{

    private String sandwich;
    private String comboSize;
    private String drinkName;

    private ArrayList<String> toppings = new ArrayList<String>();

    private double price;

    private String size;

    public Combo(String burger, ArrayList<String> topArrList, String drinkN, String size, double cost){
        this.sandwich = burger;
        this.comboSize = size;
        this.toppings = topArrList;
        this.drinkName = drinkN;
        this.price=cost;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }
    public void setDrinkName(String drinkName){
        this.drinkName=drinkName;
    }

    public void setComboSize(String comboSize) {
        this.comboSize = comboSize;
    }
    public double rtnPrice(){
        return this.price;
    }
    public String rtnItems(){
        return (this.size + " " + this.sandwich + " with a " + this.drinkName);
    }
}