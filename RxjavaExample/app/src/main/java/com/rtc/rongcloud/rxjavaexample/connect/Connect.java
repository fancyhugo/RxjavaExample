package com.rtc.rongcloud.rxjavaexample.connect;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;

public class Connect {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. publish()
        // 创建ConnectableObservable
        ConnectableObservable<Integer> connectableObservable = Observable.range(1, 5)
                .publish();    // publish操作将Observable转化为一个可连接的Observable

        // 创建普通的Observable
        Observable<Integer> range = Observable.range(1, 5);

        // 1.1 connectableObservable在被订阅时并不开始发射数据，只有在它的 connect() 被调用时才开始
        connectableObservable.subscribe(new Observer<Integer>() {

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
                System.out.println("--> onComplete(1)");
            }
        });

        // 1.2 connectableObservable在被订阅时并不开始发射数据，只有在它的 connect() 被调用时才开始
        connectableObservable.subscribe(new Observer<Integer>() {

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
                System.out.println("--> onComplete(2)");
            }
        });

        // 1.3 普通Observable在被订阅时就会发射数据
        range.subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("--> onSubscribe(3)");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("--> onNext(3): " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("--> onError(3): " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("--> onComplete(3)");
            }
        });

        System.out.println("----------------start connect------------------");
        // 可连接的Observable在被订阅时并不开始发射数据，只有在它的connect()被调用时才开始发射数据
        // connectableObservable.connect();

        // 可选参数Consumer，返回一个Disposable对象，可以获取订阅状态和取消当前的订阅
        connectableObservable.connect(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                System.out.println("--> connect accept: " + disposable.isDisposed());
                // disposable.dispose();
            }
        });
    }
}
