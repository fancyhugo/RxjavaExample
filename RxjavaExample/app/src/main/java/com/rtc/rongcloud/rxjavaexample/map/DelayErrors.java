package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class DelayErrors {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 3. flatMap(Function, delayErrors)
        // delayErrors 这个参数指定是否延迟发生Error的Observable通知
        // 当true 时延迟发生Error的这个订阅的Observable通知，不中断当前的订阅操作，
        // 继续下一个Observable的订阅，在所有订阅的Observable全部结束后发送Error这个Observable的通知
        Observable.range(1, 5)
                .flatMap(new Function<Integer, ObservableSource<? extends Integer>>() {

                    @Override
                    public ObservableSource<? extends Integer> apply(Integer t) throws Exception {
                        System.out.println("--> apply(3): " + t);

                        return Observable.create(new ObservableOnSubscribe<Integer>() {

                            @Override
                            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                                if (t == 3) {
                                    throw new NullPointerException("delayErrors test!");    // 测试 Error
                                }
                                for (int i = 1; i <= t; i++) {
                                    emitter.onNext(i);
                                }
                                emitter.onComplete();
                            }
                        });
                    }
                    // 设置延迟 Error 通知到最后
                }, true).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept flatMap(3): " + t);
            }
        }, new Consumer<Throwable>() {

            @Override
            public void accept(Throwable t) throws Exception {
                System.out.println("--> acceot Error(3): " + t);
            }
        });
    }
}
