package com.example.krustykrabsapp;

public class Item {
    final int SMALL_SIZE = 0;
    final int MEDIUM_SIZE = 1;
    final int LARGE_SIZE = 2;

    private String name;
    private double price;
    private boolean sauce;
    private int size;
    public Item(String name,double price){
        this.name = name;
        this.price = price;
    }
    public Item(String name, double price, boolean sauceCheese){
        this.name = name;
        this.price=price;
        this.sauce = sauceCheese;
    }
    public Item(String name, double price, int size){
        this.name = name;
        this.price = price;
        this.size = size;
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
    public void setSauce(boolean sauce) {
        this.sauce = sauce;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
