package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class WindowCount {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 3. window(count)
        // 以count为缓存大小收集的不重叠的Observables对象，接受的数据与原数据彼此对应
        Observable.range(1, 20)
                .window(5)	// 设置缓存大小为5
                .subscribe(new Consumer<Observable<Integer>>() {

                    @Override
                    public void accept(Observable<Integer> t) throws Exception {
                        System.out.println("--------------> new data window");
                        t.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer t) throws Exception {
                                System.out.println("--> accept window(3): " + t);
                            }
                        });
                    }
                });

    }
}
