package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Sample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 2. sample(ObservableSource sampler)
        // 每当第二个 sampler 发射一个数据（或者当它终止）时就对原始 Observable进行采样
        Observable.intervalRange(1, 5, 0, 1020, TimeUnit.MILLISECONDS)
                .doOnNext(new Consumer<Long>() {

                    @Override
                    public void accept(Long t) throws Exception {
                        System.out.println("--> DataSource onNext: " + t);
                    }
                }).sample(Observable.interval(2, TimeUnit.SECONDS))	// 每隔2秒进行一次采样
                .subscribe(new Consumer<Long>() {

                    @Override
                    public void accept(Long t) throws Exception {
                        System.out.println("--> accept(2): " + t);
                    }
                });
    }
}
