package com.rtc.rongcloud.rxjavaexample.map;

import android.annotation.SuppressLint;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class BufferClosingSelector {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        // 7. buffer(Callable<ObservableSource<T>> boundarySupplier)
        // 当它订阅原来的Observable时，开始将数据收集到一个List，然后它调用 bufferClosingSelector 生成第二个Observable，
        // 当第二个Observable 发射一个 TClosing 时，buffer 发射当前的 List ，
        // 然后重复这个过程：开始组装一个新的List，然后调用bufferClosingSelector创建一个新的Observable并监视它。
        // 它会一直这样做直到原来的Observable执行完成。会收集完整的原始 Observable 的数据
        Observable.range(1, 50000)
                .buffer(new Callable<Observable<Long>>() {

                    @Override
                    public Observable<Long> call() throws Exception {
                        return Observable.timer(1, TimeUnit.MILLISECONDS);
                    }
                }).subscribe(new Consumer<List<Integer>>() {

            @Override
            public void accept(List<Integer> t) throws Exception {
                System.out.println("--> accept(7): " + t.size());	// 每次收集的数据序列个数
            }
        });
    }
}
