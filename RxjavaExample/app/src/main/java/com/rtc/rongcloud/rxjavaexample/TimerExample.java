package com.rtc.rongcloud.rxjavaexample;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TimerExample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // timer(long delay, TimeUnit unit, Scheduler scheduler)
        // 定时delay时间 单位后发送数字0，指定可选参数Schedule调度器为trampoline(当前线程排队执行)
        Observable.timer(5, TimeUnit.SECONDS, Schedulers.trampoline())
                .subscribe(new Consumer<Long>() {

                    @Override
                    public void accept(Long t) throws Exception {
                        System.out.println("--> accept: " + t);
                    }
                });
    }
}
