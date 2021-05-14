package com.rtc.rongcloud.rxjavaexample.filter;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;



import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class Filter {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // filter(Predicate<? super Integer> predicate)
        // 验证数据,决定是否发射数据
        Observable.range(1, 10)
                .filter(new Predicate<Integer>() {

                    @Override
                    public boolean test(Integer t) throws Exception {
                        // 进行测试验证是否需要发射数据
                        return t > 5 ? true : false;
                    }
                }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer t) throws Exception {
                System.out.println("--> accept filter: " + t);
            }
        });
    }
}
