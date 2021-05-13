package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class FlatMapIterable {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        //	4. flatMapIterable(Function(T，R))
        // 	对数据进行处理转换成Iterable来发射数据
        Observable.range(1, 5)
                .flatMapIterable(new Function<Integer, Iterable<? extends Integer>>() {

                    @Override
                    public Iterable<? extends Integer> apply(Integer t) throws Exception {
                        System.out.println("--> apply: " + t);
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(888);
                        list.add(999);
                        return list; // 将原始数据转换为两个数字发送
                    }
                }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept flatMapIterable(4)： " + t);
            }
        });
    }
}
