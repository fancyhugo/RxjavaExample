package com.rtc.rongcloud.rxjavaexample.Carson_Ho;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Just {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        //Observable.just(1, 2, 3, 4)
        Observable.just(1)
                // 至此，一个Observable对象创建完毕，以下步骤仅为展示一个完整demo，可以忽略
                // 2. 通过通过订阅（subscribe）连接观察者和被观察者
                // 3. 创建观察者 & 定义响应事件的行为
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("对Complete事件作出响应");
                    }

                });
    }
}
