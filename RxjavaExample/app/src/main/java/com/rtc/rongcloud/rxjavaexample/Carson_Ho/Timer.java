package com.rtc.rongcloud.rxjavaexample.Carson_Ho;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Timer {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 该例子 = 延迟2s后，发送一个long类型数值
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        System.out.println("接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("对Complete事件作出响应");
                    }

                });

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {

        }

    }
}
