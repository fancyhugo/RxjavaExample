package com.rtc.rongcloud.rxjavaexample.combine;

public class ItemC extends Item{
    private String name;
    public ItemC(String name){
      this.name = name;
    }

    @Override
    public String toString() {
        return "ItemC{" +
                "name='" + name + '\'' +
                '}';
    }
}
