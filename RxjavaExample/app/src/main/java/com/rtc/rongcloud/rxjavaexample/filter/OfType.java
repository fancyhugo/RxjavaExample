package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class OfType {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Object[] dataObjects = {1, "Hello", 2.1f, 8.88, "1", new Integer(5)};
        // ofType(Class clazz)
        // 过滤数据，只返回特定类型的数据
        Observable.fromArray(dataObjects)
                .ofType(Integer.class) // 过滤Integer类型的数据
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("--> accept ofType: " + t);
                    }
                });

    }
}
