package com.rtc.rongcloud.rxjavaexample.combine;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Demo {
    @SuppressLint("CheckResult")
    public static void main(String args[]) {
        NetWorkApi.getColumns()
                .map(types -> {
                    List<Observable<? extends List<? extends Item>>> requestObservableList = new ArrayList<>();
                    for (String type : types) {
                        switch (type) {
                            case "a":
                                requestObservableList.add(
                                        NetWorkApi.getItemListOfTypeA().startWith(new ArrayList<ItemA>())
                                );
                                break;
                            case "b":
                                requestObservableList.add(
                                        NetWorkApi.getItemListOfTypeB().startWith(new ArrayList<ItemB>())
                                );
                                break;
                            case "c":
                                requestObservableList.add(
                                        NetWorkApi.getItemListOfTypeC().startWith(new ArrayList<ItemC>())
                                );
                                break;
                        }
                    }
                    return requestObservableList;
                })
                .flatMap(requestObservables -> Observable.combineLatest(requestObservables, objects -> {
                    List<Item> items = new ArrayList<>();
                    for (Object response : objects) {
                        items.addAll((List<? extends Item>) response);
                    }
                    return items;
                }))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(data -> {
                    System.out.println(data);
                });
        try {
            Thread.sleep(2000_000);
        } catch (InterruptedException e) {

        }
    }
}
