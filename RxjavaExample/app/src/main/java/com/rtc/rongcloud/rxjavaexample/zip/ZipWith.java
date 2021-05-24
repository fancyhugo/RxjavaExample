package com.rtc.rongcloud.rxjavaexample.zip;

import android.annotation.SuppressLint;

import androidx.core.util.Pair;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

public class ZipWith {
    private static boolean like;

    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        PublishSubject<Boolean> likeAction = PublishSubject.create();
        Observable<Boolean> debounced = likeAction.debounce(1000, TimeUnit.MILLISECONDS);
        debounced.zipWith(
                debounced.startWith(like),
                new BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>>() {
                    @NonNull
                    @Override
                    public Pair<Boolean, Boolean> apply(@NonNull Boolean last, @NonNull Boolean current) throws Exception {
                        return last == current ? new Pair<>(false, false) : new Pair<>(true, current);
                    }
                }
        ).flatMap(new Function<Pair<Boolean, Boolean>, ObservableSource<? extends Boolean>>() {
            @Override
            public ObservableSource<? extends Boolean> apply(@NonNull Pair<Boolean, Boolean> pair) throws Exception {
                return pair.first ? Observable.just(pair.second) : Observable.empty();
            }
        })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean like) throws Exception {
                        if (like) {

                        } else {

                        }
                    }
                });
    }
}
