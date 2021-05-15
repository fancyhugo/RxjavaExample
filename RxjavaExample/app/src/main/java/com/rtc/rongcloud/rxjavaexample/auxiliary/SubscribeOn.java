package com.rtc.rongcloud.rxjavaexample.auxiliary;

import android.annotation.SuppressLint;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SubscribeOn {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 查看当前线程id
        System.out.println("----> main: threadID = " + Thread.currentThread().getId());

        /**
         *  subscribeOn(Scheduler scheduler)
         *  指定Observable在指定的scheduler上调度
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 查看Observable的工作线程id
                System.out.println("----> SubscribeOn: threadID = " + Thread.currentThread().getId());
                emitter.onNext(999);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())  // 指定Observable的工作线程在子线程
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept: " + integer);
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {

        }
    }
}
