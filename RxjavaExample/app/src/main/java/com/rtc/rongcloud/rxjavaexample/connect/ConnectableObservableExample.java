package com.rtc.rongcloud.rxjavaexample.connect;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableObservableExample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. publish()
        // 创建ConnectableObservable
        ConnectableObservable<Integer> connectableObservable = Observable.range(1, 5)
                .publish();    // publish操作将Observable转化为一个可连接的Observable

        // 2. publish(Function<Observable<T>, ObservableSource<R>> selector)
        // 接受原始Observable的数据，产生一个新的Observable，可以对这个Observable进行函数处理
        Observable<String> publish = Observable.range(1, 5)
                .publish(new Function<Observable<Integer>, ObservableSource<String>>() {

                    @Override
                    public ObservableSource<String> apply(Observable<Integer> integerObservable) throws Exception {
                        System.out.println("--> apply(4): " + integerObservable.toString());

                        Observable<String> map = integerObservable.map(new Function<Integer, String>() {

                            @Override
                            public String apply(Integer integer) throws Exception {
                                return "[this is map value]: " + integer * integer;
                            }
                        });
                        return map;
                    }
                });

        publish.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("--> accept(4): " + s);
            }
        });

    }
}
