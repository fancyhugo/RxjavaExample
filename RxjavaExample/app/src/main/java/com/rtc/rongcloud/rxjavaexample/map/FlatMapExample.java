package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class FlatMapExample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
// 	1. flatMap(Function)
        // 	对原始Observable发射的每一项数据执行变换操作，这个函数返回一个本身也发射数据的Observable，
        // 	然后FlatMap合并这些Observables发射的数据，最后将合并后的结果当做它自己的数据序列发射
        Observable.range(1, 5)
                .flatMap(new Function<Integer, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(@NonNull Integer integer) throws Exception {
                        System.out.println("--> apply(1): " + integer);                            // 原始数据
                        return Observable.range(1, integer).subscribeOn(Schedulers.newThread());    // 处理后数据
                    }
                }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept flatMap(1): " + t);                    // 接受的所有数据
            }
        });
        Observable.range(1, 5)
                .flatMap(new Function<Integer, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(@NonNull Integer integer) throws Exception {
                        System.out.println("--> apply(1): " + integer);
                        return Observable.range(1, integer).subscribeOn(Schedulers.newThread());
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("--> accept flatMap(1): " + integer);                    // 接受的所有数据
            }
        });
    }
}
