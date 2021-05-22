package com.rtc.rongcloud.rxjavaexample.combine;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Demo {
    @SuppressLint("CheckResult")
    public static void main(String args[]) {
        NetWorkApi.getColumns()
                .map(new Function<List<String>, List<Observable<? extends List<? extends Item>>>>() {
                    @Override
                    public List<Observable<? extends List<? extends Item>>> apply(@NonNull List<String> types) throws Exception {
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
                    }
                })
                .flatMap(new Function<List<Observable<? extends List<? extends Item>>>, ObservableSource<? extends List<Item>>>() {
                    @Override
                    public ObservableSource<? extends List<Item>> apply(@NonNull List<Observable<? extends List<? extends Item>>> requestObservables) throws Exception {
                        return Observable.combineLatest(requestObservables, new Function<Object[], List<Item>>() {
                            @Override
                            public List<Item> apply(@NonNull Object[] objects) throws Exception {
                                List<Item> items = new ArrayList<>();
                                for (Object response : objects) {
                                    items.addAll((List<? extends Item>) response);
                                }
                                return items;
                            }
                        });
                    }
                })
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
