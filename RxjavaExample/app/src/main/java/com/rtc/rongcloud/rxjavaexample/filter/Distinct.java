package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Distinct {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. distinct()
        // 去除全部数据中重复的数据
        Observable.just(1, 2, 3, 3, 3, 4, 4, 5, 6, 6)
                .distinct()
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept distinct(1): " + t);
                    }
                });
    }
}
