package com.rtc.rongcloud.rxjavaexample.combine;

public class ItemA extends  Item{
    private String name;
    public ItemA(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "ItemA{" +
                "name='" + name + '\'' +
                '}';
    }
}
