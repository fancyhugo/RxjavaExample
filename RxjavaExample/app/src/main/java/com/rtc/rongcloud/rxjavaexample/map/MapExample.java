package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MapExample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // map(Function<T,R))
        // 接受原始Observable的数据，发送处理后的数据
        Observable.range(1, 5)
                .map(new Function<Integer, Integer>() {

                    @Override
                    public Integer apply(Integer t) throws Exception {
                        System.out.println("--> apply: " + t);
                        return t * t;    // 计算原始数据的平方
                    }
                }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept Map: " + t);
            }
        });
    }
}
