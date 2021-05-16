package com.rtc.rongcloud.rxjavaexample.condition;

import android.annotation.SuppressLint;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TakeUntil {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 创建Observable，发送数字1~10，每间隔200毫秒发射一个数据
        Observable<Long> observable = Observable.intervalRange(1, 10, 0, 200, TimeUnit.MILLISECONDS);

        /**
         *  1. takeUntil(ObservableSource other)
         *  发射来自原始Observable的数据，直到other发射了一个数据或一个通知后停止发射原始Observable并终止。
         */
        observable.takeUntil(Observable.timer(1000, TimeUnit.MILLISECONDS)) // 1000毫秒后停止发射原始数据
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("--> onSubscribe(1)");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("--> onNext(1): " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--> onError(1): " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("--> onComplete(1)");
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {

        }
    }
}
