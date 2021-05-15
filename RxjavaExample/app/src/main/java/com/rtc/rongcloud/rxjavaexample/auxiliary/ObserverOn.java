package com.rtc.rongcloud.rxjavaexample.auxiliary;

import android.annotation.SuppressLint;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ObserverOn {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 查看当前线程id
        System.out.println("----> main: threadID = " + Thread.currentThread().getId());

        /**
         *  observeOn(Scheduler scheduler,
         *  boolean delayError,     // 可选参数是否延迟异常
         *  int bufferSize          		  // 指定缓存大小
         *  )
         * 指定观察者在指定的scheduler线程中调度
         */
        Observable.just(999).observeOn(Schedulers.newThread(), true, 3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        // 查看观察者的线程id
                        System.out.println("--> accept ThreadID: " + Thread.currentThread().getId());
                        System.out.println("--> accept: " + integer);
                    }
                });


        try {
            System.in.read();
        } catch (IOException e) {

        }
    }

}
