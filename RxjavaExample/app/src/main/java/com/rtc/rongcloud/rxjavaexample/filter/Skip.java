package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Skip {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. skip(long count)
        // 跳过前count项数据，保留后面的数据
        Observable.range(1, 10)
                .skip(5) // 过滤数据序列前5项数据
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept skip(1): " + t);
                    }
                });
    }
}
