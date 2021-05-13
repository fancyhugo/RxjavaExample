package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ConcatMap {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. concatMap(Function(T,R))
        // 按照顺序依次处理原始数据和处理的数据
        Observable.range(1, 3)
                .concatMap(new Function<Integer, ObservableSource<? extends Integer>>() {

                    @Override
                    public ObservableSource<? extends Integer> apply(Integer t) throws Exception {
                        System.out.println("--> apply(1): " + t);
                        return Observable.range(1, t).doOnSubscribe(new Consumer<Disposable>() {

                            @Override
                            public void accept(Disposable t) throws Exception {
                                System.out.println("--> accept(1): Observable on Subscribe");    // 当前的Observable被订阅
                            }
                        });
                    }
                }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept concatMap(1): " + t);
            }
        });

        System.out.println("--------------------------------------------");
        // 2. concatMap(mapper, prefetch)
        // prefetch 参数是在处理后的Observables发射的数据流中预读数据个数，不影响原数据的发射和接收顺序
        Observable.range(1, 3)
                .concatMap(new Function<Integer, ObservableSource<? extends Integer>>() {

                    @Override
                    public ObservableSource<? extends Integer> apply(Integer t) throws Exception {
                        System.out.println("--> apply(2): " + t);
                        return Observable.range(1, 3).doOnSubscribe(new Consumer<Disposable>() {

                            @Override
                            public void accept(Disposable t) throws Exception {
                                System.out.println("--> accept(2): Observable on Subscribe");    // 当前的Observable被订阅
                            }
                        });
                    }
                }, 2).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept concatMap(2): " + t);
            }
        });
    }
}
