package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

public class GroupBy {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. groupBy(keySelector)
        // 将原始数据处理后加上分组tag，通过GroupedObservable发射分组数据
        Observable.range(1, 10)
                .groupBy(new Function<Integer, String>() {

                    @Override
                    public String apply(Integer t) throws Exception {
                        // 不同的key将会产生不同分组的Observable
                        return t % 2 == 0 ? "Even" : "Odd"; // 将数据奇偶数进行分组,
                    }
                }).observeOn(Schedulers.newThread())
                .subscribe(new Consumer<GroupedObservable<String, Integer>>() {

                    @Override
                    public void accept(GroupedObservable<String, Integer> grouped) throws Exception {
                        // 得到每个分组数据的的Observable
                        grouped.subscribe(new Consumer<Integer>() {

                            @Override
                            public void accept(Integer t) throws Exception {
                                // 得到数据
                                System.out.println("--> accept groupBy(1):   groupKey: " + grouped.getKey() + ", value: " + t);
                            }
                        });
                    }
                });
    }
}
