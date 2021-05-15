package com.rtc.rongcloud.rxjavaexample.auxiliary;

import android.annotation.SuppressLint;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Timeout {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        /**
         *  1. timeout(long timeout, TimeUnit timeUnit)
         *  接受一个时长参数，如果在指定的时间段内没有数据项发射，将会发射一个Error通知，
         *  或者每当原始Observable发射了一项数据， timeout 就启动一个计时器，
         *  如果计时器超过了指定指定的时长而原始Observable没有发射另一项数据，
         *  就抛出 TimeoutException ，以一个错误通知终止Observable。
         */
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                //  Thread.sleep(2000);     // 延迟2秒后发射数据，此时会有TimeoutException
                emitter.onNext(1L);
                Thread.sleep(2000);     // 延迟2秒后发射数据，此时会有TimeoutException
                emitter.onNext(2L);
                emitter.onComplete();
            }
        }).timeout(1, TimeUnit.SECONDS)     // 指定超时时间段为1秒
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("--> onSubscribe(1)");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("--> onNext(1): " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--> onError(1): " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("--> onComplete(1)");
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {

        }
    }
}
