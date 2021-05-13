package com.rtc.rongcloud.rxjavaexample;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class IntervalExample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // [1] interval(long period, TimeUnit unit)
        // 每间隔period时间单位，发射一次整数序列
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {

                    public void accept(Long l) throws Exception {
                        System.out.println("--> accept(1): " + l);
                    }
                });

        System.out.println("------------------------------------");
        // [2] interval(long initialDelay, long period, TimeUnit unit)
        // 在延迟initialDelay秒后每隔period时间单位发射一个整数序列
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {

                    public void accept(Long t) throws Exception {
                        System.out.println("--> accept(2): " + t);
                    }
                });

        System.out.println("------------------------------------");
        // [3] intervalRange(long start, long count, long initialDelay, long period, TimeUnit unit)
        // 延迟initialDelay秒后从起始数据start开始，每隔period秒发送一个数字序列，一共发送count个数据
        Observable.intervalRange(1, 5, 3, 2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {

                    public void accept(Long t) throws Exception {
                        System.out.println("--> accept(3): " + t);
                    }
                });
    }
}
