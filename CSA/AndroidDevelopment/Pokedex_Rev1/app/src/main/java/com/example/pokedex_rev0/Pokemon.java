package com.example.pokedex_rev0;

public class Pokemon {
    private String name;
    private short number;
    private String url;

    public Pokemon(String name, short number) {
        this.name = name;
        this.number = number;
    }

    public Pokemon(String name, String url){
        this.name = name;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = (short) number;
    }

    public String getName() {
        return name;
    }

    public short getNumber() {
        return number;
    }

    public String getUrl() {
        return url;
    }
}

