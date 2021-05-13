package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Window {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. window(Callable boundary)
        // 开启一个window，并订阅观察boundary返回的Observable发射了一个数据，
        // 则关闭此window，将收集的数据以Observable发送, 重新订阅boundary返回的Observable，开启新window
        Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS)
                .window(new Callable<Observable<Long>>() {

                    @Override
                    public Observable<Long> call() throws Exception {
                        System.out.println("--> call(1)");
                        return Observable.timer(2, TimeUnit.SECONDS); // 两秒后关闭当前窗口
                    }
                }).subscribe(new Consumer<Observable<Long>>() {

            @Override
            public void accept(Observable<Long> t) throws Exception {
                // 接受每个window接受的数据的Observable
                t.subscribe(new Consumer<Long>() {

                    @Override
                    public void accept(Long t) throws Exception {
                        System.out.println("--> accept(1): " + t);
                    }
                });
            }
        });
    }
}
