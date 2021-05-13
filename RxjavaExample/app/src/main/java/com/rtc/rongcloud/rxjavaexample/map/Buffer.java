package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Buffer {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. buffer(count)
        // 以列表(List)的形式发射非重叠的缓存，
        // 每一个缓存至多包含来自原始 Observable的count项数据（最后发射的列表数据可能少于count项）
        Observable.range(1, 10)
                .buffer(3)
                .subscribe(new Consumer<List<Integer>>() {

                    @Override
                    public void accept(List<Integer> t) throws Exception {
                        System.out.println("--> bufferr(1) accept: " + t);
                    }
                });
    }
}
