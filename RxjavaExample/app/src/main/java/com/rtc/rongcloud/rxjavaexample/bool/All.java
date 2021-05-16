package com.rtc.rongcloud.rxjavaexample.bool;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class All {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        /**
         *  all(Predicate predicate)
         *  通过传入的谓语函数predicate进行判断所有数据项是否满足条件，然后返回一个判断结果发射给观察者
         */
        Observable.just(1, 2, 3, 4, 5)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 5; // 判断原始数据项中的所有数据项是否大于5
                    }
                })
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
