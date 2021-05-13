package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Timespan {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 4. buffer(long timespan, TimeUnit unit)
        // 每隔timespan时间段以list的形式收集数据
        Observable.range(1, 50000)
                .buffer(1, TimeUnit.MILLISECONDS)                                    // 每隔1毫秒收集一次原始序列数据
                .subscribe(new Consumer<List<Integer>>() {

                    @Override
                    public void accept(List<Integer> t) throws Exception {
                        System.out.println("--> bufferr(4) accept: " + t.size());    // 每次收集的数据序列个数
                    }
                });
    }
}
