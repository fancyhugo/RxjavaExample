package com.rtc.rongcloud.rxjavaexample.combine;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Demo1 {
    @SuppressLint("CheckResult")
    public static void main(String args[]) {
        Disposable subscribe = NetWorkApi.getColumns()
                .flatMap(types -> Observable.fromIterable(types)
                        .map(type -> {
                            switch (type) {
                                case "a":
                                    return NetWorkApi.getItemListOfTypeA().startWith(new ArrayList<ItemA>());
                                case "b":
                                    return NetWorkApi.getItemListOfTypeB().startWith(new ArrayList<ItemB>());
                                case "c":
                                    return NetWorkApi.getItemListOfTypeC().startWith(new ArrayList<ItemC>());
                                default:
                                    throw new IllegalArgumentException();
                            }
                        })
                        .<List<Observable<? extends List<? extends Item>>>>collectInto(new ArrayList<>(), List::add)
                        .toObservable()
                )
                .flatMap(requestObservables -> Observable.combineLatest(requestObservables, objects -> objects))
                .flatMap(objects -> Observable.fromArray(objects)
                        .<List<Item>>collectInto(new ArrayList<>(), (items, o) -> items.addAll((List<Item>) o))
                        .toObservable()
                )
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(data -> {
                    System.out.println(data);
                });
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {

        }
    }
}
