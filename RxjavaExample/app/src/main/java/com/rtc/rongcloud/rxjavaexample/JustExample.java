package com.rtc.rongcloud.rxjavaexample;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class JustExample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 单个对象发送
        Observable.just(1)
                .subscribe(new Consumer<Integer>() {

                    public void accept(Integer t) throws Exception {
                        System.out.println("--> singe accept: " + t);
                    }
                });

        System.out.println("---------------------------------");
        // 多个对象发送，内部实际使用from实现 (接受一至九个参数，返回一个按参数列表顺序发射这些数据的Observable)
        Observable.just(1, 2, 3, 4, 5)
                .subscribe(new Consumer<Integer>() {

                    public void accept(Integer t) throws Exception {
                        System.out.println("--> mutil accept: " + t);
                    }
                });
    }
}
