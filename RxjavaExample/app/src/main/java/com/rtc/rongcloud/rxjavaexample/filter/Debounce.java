package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class Debounce {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. debounce(long timeout, TimeUnit unit)
        // 发送一个数据，如果在包含timeout时间内，没有第二个数据发射，那么就会发射此数据，否则丢弃此数据
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);    // 下一个数据到此数据发射,	30 < timeout	--> skip
                Thread.sleep(30);
                emitter.onNext(2);    // 下一个数据到此数据发射,	100 > timeout	--> deliver
                Thread.sleep(100);
                emitter.onNext(3);    // 下一个数据到此数据发射,	50 = timeout	--> skip:
                Thread.sleep(50);
                emitter.onNext(4);    // 下一个数据到此数据发射,	onCompleted		--> deliver
                emitter.onComplete();

            }
        }).debounce(50, TimeUnit.MILLISECONDS)    // 指定防抖丢弃时间段为50毫秒
                //  .debounce(50, TimeUnit.MILLISECONDS, Schedulers.trampoline())	// 指定调度为当前线程排队
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept debounce(1-1): " + t);
                    }
                });
    }
}
