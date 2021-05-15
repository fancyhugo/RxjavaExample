package com.rtc.rongcloud.rxjavaexample.error;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class onErrorResumeNext {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 创建一个可以发射异常的Observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(1 / 0);  // 产生一个异常
                emitter.onNext(3);
                emitter.onNext(4);
            }
        });

        /**
         * 3. onErrorResumeNext(ObservableSource next)
         * 让Observable在遇到错误时开始发射第二个指定的Observable的数据序列
         */
        observable.onErrorResumeNext(Observable.just(888))  // 当发生异常的时候继续发射此项Observable
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("--> onSubscribe(3)");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("--> onNext(3): " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--> onError(3): " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("--> onCompleted(3)");
                    }
                });

        System.out.println("-----------------------------------------------");
        /**
         * 4. onErrorResumeNext(Function<Throwable, ObservableSource<T>> resumeFunction)
         * 让Observable在遇到错误时通过一个函数Function来接受Error参数并进行判断返回指定的第二个Observable的数据序列
         */
        observable.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                System.out.println("--> apply(4): " + throwable);
                return Observable.just(888);    // 当发生异常的时候继续发射此项Observable
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("--> onSubscribe(4)");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("--> onNext(4): " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("--> onError(4): " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("--> onCompleted(4)");
            }
        });
    }
}
