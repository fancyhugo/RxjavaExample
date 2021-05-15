package com.rtc.rongcloud.rxjavaexample.error;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class onErrorReturn {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 创建一个可以发射异常的Observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(1 / 0);  // 产生一个异常
                emitter.onNext(3);
                emitter.onNext(4);
            }
        });

        /** 1. onErrorReturnItem(T item)
         * 让Observable遇到错误时发射一个指定的项（item）并且正常终止。
         */
        observable.onErrorReturnItem(888)   // 源Observable发生异常时发射指定的888数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("--> onSubscribe(1)");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("--> onNext(1): " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--> onError(1): " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("--> onCompleted(1)");
                    }
                });

        System.out.println("-----------------------------------------------");
        /**
         * 2. onErrorReturn(Function<Throwable, T> valueSupplier)
         * 让Observable遇到错误时通过一个函数Function来接受Error参数并进行判断返回指定的类型数据，并且正常终止。
         */
        observable.onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                System.out.println("--> apply(1): e = " + throwable);
                return 888; // 源Observable发生异常时发射指定的888数据
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("--> onSubscribe(2)");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("--> onNext(2): " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("--> onError(2): " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("--> onCompleted(2)");
            }
        });
    }
}
