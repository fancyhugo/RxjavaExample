package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ResultSelector {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        //	5. flatMapIterable(Function（T，R）,Function(T,T,R))
        // 	第一个func接受原始数据，转换数据，第二个func同时接受原始和处理的数据，进行二次转换处理
        Observable.range(1, 3)
                .flatMapIterable(new Function<Integer, Iterable<? extends Integer>>() {

                    @Override
                    public Iterable<? extends Integer> apply(Integer t) throws Exception {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(888);
                        list.add(999);
                        return list; // 将原始数据转换为两个数字发送
                    }
                }, new BiFunction<Integer, Integer, Integer>() {

                    @Override
                    public Integer apply(Integer t1, Integer t2) throws Exception {
                        System.out.println("--> apply(5): t1 = " + t1 + ", t2 = " + t2);
                        return t1 + t2;	// 将原始数据和处理过的数据组合进行二次处理发送
                    }
                }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept flatMapIterable(5)： " + t);
            }
        });
    }
}
