package com.rtc.rongcloud.rxjavaexample.merge;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;

public class Merge {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 创建Observable对象
        Observable<Integer> odd = Observable.just(1, 3, 5);
        Observable<Integer> even = Observable.just(2, 4, 6);
        Observable<Integer> big = Observable.just(188888, 688888, 888888);

        // 创建list对象
        List<Observable<Integer>> list = new ArrayList<>();
        list.add(odd);
        list.add(even);
        list.add(big);

        // 创建Array对象
        Observable<Integer>[] observables = new Observable[3];
        observables[0] = odd;
        observables[1] = even;
        observables[2] = big;

        // 创建发射Observable序列的Observable
        Observable<ObservableSource<Integer>> sources = Observable.create(new ObservableOnSubscribe<ObservableSource<Integer>>() {
            @Override
            public void subscribe(ObservableEmitter<ObservableSource<Integer>> emitter) throws Exception {
                emitter.onNext(Observable.just(1));
                emitter.onNext(Observable.just(1, 2));
                emitter.onNext(Observable.just(1, 2, 3));
                emitter.onNext(Observable.just(1, 2, 3, 4));
                emitter.onNext(Observable.just(1, 2, 3, 4, 5));
                emitter.onComplete();
            }
        });

        // 1. merge(ObservableSource source1, ObservableSource source2, ..., ObservableSource source4)
        // 可接受 2-4 个Observable对象进行merge
        Observable.merge(odd, even)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept(1): " + integer);
                    }
                });

        System.out.println("-----------------------------------------------");
        // 2. merge(Iterable<? extends ObservableSource<? extends T>> sources, int maxConcurrency, int bufferSize)
        // 可选参数, maxConcurrency: 最大的并发处理数, bufferSize: 缓存的数量（从每个内部观察资源预取的项数）
        // 接受一个Observable的列表List
        Observable.merge(list)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept(2): " + integer);
                    }
                });

        System.out.println("-----------------------------------------------");
        // 3. mergeArray(int maxConcurrency, int bufferSize, ObservableSource<? extends T>... sources)
        // 可选参数, maxConcurrency: 最大的并发处理数, bufferSize: 缓存的数量（从每个内部观察资源预取的项数）
        // 接受一个Observable的数组Array
        Observable.mergeArray(observables)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept(3): " + integer);
                    }
                });

        System.out.println("-----------------------------------------------");
        // 4. merge(ObservableSource<? extends ObservableSource<? extends T>> sources, int maxConcurrency)
        // 可选参数, maxConcurrency: 最大的并发处理数
        // 接受一个发射Observable序列的Observable
        Observable.merge(sources)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept(4): " + integer);
                    }
                });

        System.out.println("-----------------------------------------------");
        // 5. mergeWith(other)
        // merge 是静态方法， mergeWith 是对象方法: Observable.merge(odd,even) 等价于 odd.mergeWith(even)
        odd.mergeWith(even)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("--> accept(5): " + integer);
                    }
                });
    }
}
