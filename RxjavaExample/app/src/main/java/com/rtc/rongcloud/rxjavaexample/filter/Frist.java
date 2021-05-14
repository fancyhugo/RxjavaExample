package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Frist {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. firstElement()
        // 只发射第一个数据
        Observable.range(1, 10)
                .firstElement()
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept firstElement(1): "  + t);
                    }
                });
    }
}
