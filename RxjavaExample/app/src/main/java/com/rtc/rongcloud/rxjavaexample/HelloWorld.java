package com.rtc.rongcloud.rxjavaexample;


import android.util.Log;



import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class HelloWorld {
    private static final String TAG = "HelloWorld";

    public static void main(String[] args) {
//        create_demo();
//        just_demo();
//        from_demo();
//        defer_demo();
//        range_demo();
        repeat_demo();
    }

    private static void repeat_demo() {
        Observable.range(1, 3).repeat(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError...");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted...");
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Integer arg) {
                        Log.d(TAG, "onNext" + arg.toString());
                    }
                });
    }

    private static void range_demo() {
        Observable.range(1, 5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError...");
                    }


                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Integer arg) {
                        Log.d(TAG, "onNext: {}"+arg);
                    }
                });
    }

    private static String value;

    private static void defer_demo() {
        Observable<String> observable = Observable.defer(() -> Observable.just(value));

        value = "Hello World.";

        observable.subscribe(new Observer<String>() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted...");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError...");
            }


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: {}"+s);
            }
        });
    }

    private static void from_demo() {
        // 文档：https://reactivex.io/documentation/operators/from.html
        Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6})
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError...");
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Integer arg) {
                        Log.d(TAG, "onNext: {}"+ arg);
                    }
                });
    }

    private static void just_demo() {
        Observable.just("RxJava学习...")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError...");
                    }


                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: {}"+ s);
                    }
                });
    }

    private static void create_demo() {
        Observable.create((ObservableOnSubscribe<String>) subscriber -> subscriber.onNext("RxJava学习..."))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError...");
                    }


                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext:" + s);
                    }
                });
    }
}
