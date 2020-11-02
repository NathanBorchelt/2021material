package com.example.krustykrabsapp;

public class Item {
    final int SMALL_SIZE = 0;
    final int MEDIUM_SIZE = 1;
    final int LARGE_SIZE = 2;

    private String name;
    private double price;
    private double originalPrice;
    private boolean sauce;
    private boolean meal;
    private int size;


    public Item(){
        this.name = "";
        this.price = 0.;
    }

    public Item(String name,double price){
        this.name = name;
        this.price = price;
        this.originalPrice = price;
    }
    public Item(String name, double price, boolean sauceCheese){
        this.name = name;
        this.price=price;
        this.sauce = sauceCheese;
        this.originalPrice = price;
    }
    public Item(String name, double price, int size){
        this.name = name;
        this.price = price;
        this.size = size;
        this.originalPrice = price;
    }
//getters
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    //setters
    public void setMeal(boolean meal){ this.meal = meal;}

    public void setSauce(boolean sauce) {this.sauce=sauce;}

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void upcharge(double uCPrice){
        this.price += uCPrice;
    }
    public void recharge(double uCPrice){
        this.price = this.originalPrice;
        this.price += uCPrice;
    }
}
