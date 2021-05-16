package com.rtc.rongcloud.rxjavaexample.condition;

import android.annotation.SuppressLint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Amb {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 创建Observable
        Observable<Integer> delayObservable = Observable.range(1, 5)
                .delay(100, TimeUnit.MILLISECONDS); // 延迟100毫秒发射数据
        Observable<Integer> rangeObservable = Observable.range(6, 5);

        // 创建Observable的集合
        ArrayList<Observable<Integer>> list = new ArrayList<>();
        list.add(delayObservable);
        list.add(rangeObservable);

        // 创建Observable的数组
        Observable<Integer>[] array = new Observable[2];
        array[0] = delayObservable;
        array[1] = rangeObservable;

        /**
         *  1. ambWith(ObservableSource<? extends T> other)
         *  与另外一个Observable比较，只发射首先发射通知的Observable的数据
         */
        rangeObservable.ambWith(delayObservable)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept(1): " + integer);
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {

        }
        System.out.println("------------------------------------------------");
        /**
         *  2. amb(Iterable<? extends ObservableSource<? extends T>> sources)
         *  接受一个Observable类型的集合， 只发射集合中首先发射通知的Observable的数据
         */
        Observable.amb(list)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept(2): " + integer);
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {

        }
        System.out.println("------------------------------------------------");
        /**
         *  3. ambArray(ObservableSource<? extends T>... sources)
         *  接受一个Observable类型的数组， 只发射数组中首先发射通知的Observable的数据
         */
        Observable.ambArray(array)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept(3): " + integer);
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {

        }
    }
}
