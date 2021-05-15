package com.rtc.rongcloud.rxjavaexample.merge;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class Zip {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 创建Observable
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(1, 2, 3, 4, 5, 6);

        // zip（sources）
        // 可接受2-9个参数的Observable，对其进行顺序合并操作，最终合并的数据项取决于最少的数据项的Observable
        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer t1, Integer t2) throws Exception {
                System.out.println("--> apply: t1 = " + t1 + ", t2 = " + t2);
                return t1 + t2 + "";
            }
        }).subscribe(new Consumer<String>() {

            @Override
            public void accept(String s) throws Exception {
                System.out.println("--> accept: " + s);  // 最终接受observable1全部数据项与observable2相同数量顺序部分数据
            }
        });
    }
}
