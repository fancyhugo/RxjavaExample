package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ElementAt {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. elementAt(long index)
        // 指定发射第N项数据（从0开始计数）,如果数据不存在，会IndexOutOfBoundsException异常
        Observable.range(1, 10)
                .elementAt(5) // 发射数据序列中索引为5的数据项，索引从0开始
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept ElementAt(1): " + t);
                    }
                });
    }
}
