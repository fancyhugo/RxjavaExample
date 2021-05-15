package com.rtc.rongcloud.rxjavaexample.auxiliary;

import android.annotation.SuppressLint;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Materialize {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        /**
         *  materialize()
         *  将来自原始Observable的通知转换为Notification对象，然后它返回的Observable会发射这些数据。
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("Test Error!"));
                emitter.onComplete();
            }
        }).materialize()
                .subscribe(new Observer<Notification<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("--> onSubscribe");
                    }

                    @Override
                    public void onNext(Notification<Integer> integerNotification) {
                        System.out.println("--> onNext: " + integerNotification);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--> onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("--> onComplete");
                    }
                });
    }
}
