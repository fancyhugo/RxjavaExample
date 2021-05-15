package com.rtc.rongcloud.rxjavaexample.error;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class onExceptionResumeNext {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 创建一个可以发射异常的Observable
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                //  emitter.onError(new Throwable("This is Throwable!"));  // Throwable类型异常,直接通知观察者
                //  emitter.onError(new Error("This is Error!"));          // Error类型异常,直接通知观察者
                emitter.onError(new Exception("This is Exception!"));  // Exception类型异常,进行处理,发送备用的Observable数据
                //    emitter.onNext(1 / 0);  // 会产生一个ArithmeticException异常,异常会被处理,发送备用的Observable数据
                emitter.onNext(3);
                emitter.onNext(4);
            }
        });
        /**
         * 5. onExceptionResumeNext(ObservableSource next)
         *  如果onError收到的Throwable不是一个Exception,它会将错误传递给观察者的onError方法,不会使用备用的Observable
         *  只对Exception类型的异常通知进行备用Observable处理
         */
        observable1.onExceptionResumeNext(Observable.just(888))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("--> onSubscribe(5)");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("--> onNext(5): " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--> onError(5): " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("--> onCompleted(5)");
                    }
                });
    }

}
