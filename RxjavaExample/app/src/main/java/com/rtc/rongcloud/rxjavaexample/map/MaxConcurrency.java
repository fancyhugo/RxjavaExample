package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MaxConcurrency {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 2. flatMap(Function, maxConcurrency)
        // maxConcurrency 这个参数设置 flatMap 从原来的Observable映射Observables的最大同时订阅数。
        // 当达到这个限制时，它会等待其中一个终止然后再订阅另一个
        Observable.range(1, 5)
                .flatMap(new Function<Integer, ObservableSource<? extends Integer>>() {

                    @Override
                    public ObservableSource<? extends Integer> apply(Integer t) throws Exception {
                        System.out.println("--> apply(2): " + t);
                        return Observable.range(1, t).subscribeOn(Schedulers.newThread());
                    }
                    // 指定最大订阅数为1，此时等待上一个订阅的Observable结束，在进行下一个Observable订阅
                }, 3).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept flatMap(2): "+ t);
            }
        });
    }
}
