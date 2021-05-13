package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class CountSkip {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 3. buffer(int count, int skip)
        // 在指定的数据中移动指针来获取缓存数据：指针每次移动1个数据长度，每次缓存3个数据
        Observable.range(1, 10)
                .buffer(4, 3)
                .subscribe(new Consumer<List<Integer>>() {

                    @Override
                    public void accept(List<Integer> t) throws Exception {
                        System.out.println("--> bufferr(3) accept: " + t);
                    }
                });
    }
}
