package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Throttle {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. throttleFirst(long windowDuration, TimeUnit unit)
        // 指定每个指定时间内取第一项数据, 直到原始数据序列全部发送结束
        Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {

                    @Override
                    public void accept(Long t) throws Exception {
                        System.out.println("--> DataSource doOnNext : " + t);
                    }
                }).throttleFirst(2, TimeUnit.SECONDS)                            // 获取每隔2秒之内收集的第一项数据
                //   .throttleFirst(2, TimeUnit.SECONDS, Schedulers.newThread())	// 指定调度线程为newThread()
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("--> throttleFirst onSubscribe");
                    }

                    @Override
                    public void onNext(Long t) {
                        System.out.println("-------------> throttleFirst onNext: " + t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--> throttleFirst onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("--> throttleFirst onComplete");
                    }
                });
    }
}
