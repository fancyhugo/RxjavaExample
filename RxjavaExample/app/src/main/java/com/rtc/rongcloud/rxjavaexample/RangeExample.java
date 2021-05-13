package com.rtc.rongcloud.rxjavaexample;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RangeExample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. range(n,m) 发射从n开始的m个整数序列，序列区间[n,n+m-1)
        Observable.range(0, 5)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("-- accept(range): " + t);
                    }
                });

        System.out.println("------------------------------");
        // 2. rangeLong(n,m) 发射从n开始的m个长整型序列，序列区间[n,n+m-1)
        Observable.rangeLong(1, 5)
                .subscribe(new Consumer<Long>() {

                    @Override
                    public void accept(Long t) throws Exception {
                        System.out.println("-- accept(rangeLong): " + t);
                    }
                });
    }
}
