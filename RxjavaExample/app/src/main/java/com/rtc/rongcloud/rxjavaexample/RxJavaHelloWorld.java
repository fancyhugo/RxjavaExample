package com.rtc.rongcloud.rxjavaexample;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class RxJavaHelloWorld {
    @SuppressLint("CheckResult")
    public static void hello(String... args) {
        Flowable.fromArray(args).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Hello " + s + "!");
            }
        });
    }
    public static void main(String args[]){
        hello("Ben", "George");
    }
}
