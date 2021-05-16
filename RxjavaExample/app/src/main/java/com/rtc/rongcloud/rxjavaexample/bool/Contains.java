package com.rtc.rongcloud.rxjavaexample.bool;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class Contains {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        /**
         *  contains(Object element)
         *  判断原始Observable是否发射了指定的element数据
         */
        Observable.just(1, 2, 3, 4, 5)
                .contains(5)    // 判断原始数据项中是否有数据项5
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("--> onSubscribe");
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        System.out.println("--> onSuccess: " + aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--> onError: " + e);
                    }
                });
    }
}
