package com.rtc.rongcloud.rxjavaexample;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;

class RxJava2nd {
    private static final String TAG = "RxJavaFirst";
    public static void main(String args[]) {
        RxJavaPlugins.setErrorHandler(throwable -> {
            throwable.printStackTrace();
            Log.e("MyApplication", "MyApplication setRxJavaErrorHandler "  + throwable.getMessage());
        });
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Log.e(TAG, "发送线程：" + Thread.currentThread().getName());
                Log.e(TAG, "发送：：" + "hello");
                emitter.onNext("hello");
                Log.e(TAG, "发送：：" + "world");
                emitter.onNext("world");
                Log.e(TAG, "发送：：" + "Hello World");
                emitter.onNext("Hello World");
                Log.e(TAG, "发送：：" + "onComplete");
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "onSubscribe");
                //销毁资源，后续的方法不在执行；
                //d.dispose();
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e(TAG, "接收线程：" + Thread.currentThread().getName()); Log.e(TAG, "接收：" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };
        stringObservable.subscribe(observer);
    }
}
