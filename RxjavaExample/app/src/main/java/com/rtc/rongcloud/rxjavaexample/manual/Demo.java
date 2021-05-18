package com.rtc.rongcloud.rxjavaexample.manual;


import android.annotation.SuppressLint;

public class Demo {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Observable.create(new Observable<String>() {
            @Override
            public void subscribe(Observer<String> observer) {
                observer.onNext("hello");
                observer.onNext("world");
                observer.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
