package com.rtc.rongcloud.rxjavaexample.combine;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NetWorkApi {
    static public Observable<List<String>> getColumns() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        return Observable.just(list);
    }

    static public Observable<List<ItemA>> getItemListOfTypeA() {
        List<ItemA> list = new ArrayList<>();
        list.add(new ItemA("A1"));
        list.add(new ItemA("A2"));
        return Observable.just(list);
    }

    static public Observable<List<ItemB>> getItemListOfTypeB() {
        List<ItemB> list = new ArrayList<>();
        list.add(new ItemB("B1"));
        return Observable.just(list);
    }

    static public Observable<List<ItemC>> getItemListOfTypeC() {
        List<ItemC> list = new ArrayList<>();
        list.add(new ItemC("C1"));
        return Observable.just(list);
    }
}
