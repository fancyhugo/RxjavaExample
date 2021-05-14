package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Take {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. take(long count)
        // 返回前count项数据
        Observable.range(1, 100)
                .take(5) // 返回前5项数据
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept take(1): " + t);
                    }
                });
    }
}
