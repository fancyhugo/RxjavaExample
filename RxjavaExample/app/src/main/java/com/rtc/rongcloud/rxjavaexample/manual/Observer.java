package com.rtc.rongcloud.rxjavaexample.manual;


public interface Observer<T> {

    void onNext(T t);
    void onComplete();
}
