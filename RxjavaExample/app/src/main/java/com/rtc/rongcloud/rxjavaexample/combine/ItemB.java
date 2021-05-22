package com.rtc.rongcloud.rxjavaexample.combine;

public class ItemB extends Item{
    private String name;
    public ItemB(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "ItemB{" +
                "name='" + name + '\'' +
                '}';
    }
}
