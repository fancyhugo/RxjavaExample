package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Boundary {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 2. buffer(boundary) 监视一个名叫boundary的Observable，
        // 开始创建一个List收集原始 Observable 数据，监视一个名叫boundary的Observable，
        // 每当这个Observable发射了一个值，它就创建一个新的List开始收集来自原始Observable的数据并发射原来已经收集数据的List,
        // 当boundary发送了完成通知，会将此时还未发送的 List 发送。
        // 所有发送的 List 可能没有收集到数据，此时数据的收集可能并不会完整收集所有原始Observable数据。
        Observable.range(1, 10000)
                .buffer(Observable.timer(1, TimeUnit.MILLISECONDS)) 		// 1毫秒后开始接受原始数据
                .subscribe(new Consumer<List<Integer>>() {

                    @Override
                    public void accept(List<Integer> t) throws Exception {
                        System.out.println("--> accept(2): " + t.size());	// 每次收集的数据序列个数
                    }
                });
    }
}
