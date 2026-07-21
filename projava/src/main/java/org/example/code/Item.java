package org.example.code;

public class Item {
    private String name;

    public Item(){
        this("未設定");
    }

    public Item(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
