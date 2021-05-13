package com.rtc.rongcloud.rxjavaexample;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RepeatExample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. repeat(): 一直重复发射原始 Observable的数据序列
        Observable.range(1, 5)
                .repeat(1)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept(1): " + t);
                    }
                });

        System.out.println("----------------------------------------");
        // 2. repeat(n): 重复执行3次
        Observable.range(1, 2)
                .repeat(3)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept(2): " + t);
                    }
                });
    }
}
