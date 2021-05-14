package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class Single {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1.singleElement()
        // 发射单例数据，超过一个就会NoSuchElementException
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).singleElement() // 发送单个数据，大于1项数据就会有Error通知
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept singleElement(1): " + t);
                    }
                },new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable t) throws Exception {
                        System.out.println("--> OnError(1): " + t);
                    }
                });
    }
}
