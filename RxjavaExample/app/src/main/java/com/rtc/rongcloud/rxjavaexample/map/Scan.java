package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class Scan {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. scan(BiFunction(Integer sum, Integer t2))
        // 接受数据序列，从第二个数据开始，每次会将上次处理数据和现在接受的数据进行处理后发送
        Observable.range(1, 10)
                .scan(new BiFunction<Integer, Integer, Integer>() {

                    @Override
                    public Integer apply(Integer LastItem, Integer item) throws Exception {
                        System.out.println("--> apply: LastItem = " + LastItem + ", CurrentItem = " + item);
                        return LastItem + item; // 实现求和操作
                    }
                }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept scan(1): " + t);

            }
        });
    }
}
