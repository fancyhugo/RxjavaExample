package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class TakeLast {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. takeLast(int count)
        // 接受Observable数据发射完成前的Count项数据, 忽略前面的数据
        Observable.range(1, 10)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept(1): " + t);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("--> onCompleted(1): ");
                    }
                })
                .takeLast(5) // 发送数据发射完成前的5项数据
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept takeLast(1): " + t);
                    }
                });
    }
}
