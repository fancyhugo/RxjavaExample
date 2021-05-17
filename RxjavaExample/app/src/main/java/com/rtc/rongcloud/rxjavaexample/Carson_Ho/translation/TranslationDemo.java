package com.rtc.rongcloud.rxjavaexample.Carson_Ho.translation;

import android.annotation.SuppressLint;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslationDemo {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Observable.interval(2, 1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        System.out.println("第 " + integer + " 次轮询");

                        /*
                         * 步骤3：通过Retrofit发送网络请求
                         **/
                        // a. 创建Retrofit对象
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://dict-co.iciba.com/api/") // 设置 网络请求 Url
                                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                                .build();

                        // b. 创建 网络请求接口 的实例
                        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

                        // c. 采用Observable<...>形式 对 网络请求 进行封装
                        Observable<Translation> observable = request.getCall();
                        // d. 通过线程切换发送网络请求
                        observable.subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                                .observeOn(Schedulers.newThread())  // 切换回到主线程 处理请求结果
                                .subscribe(new Observer<Translation>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        System.out.println("GetRequest onSubscribe");
                                    }

                                    @Override
                                    public void onNext(Translation result) {
                                        // e.接收服务器返回的数据
                                        result.show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        System.out.println("GetRequest 请求失败");
                                    }

                                    @Override
                                    public void onComplete() {
                                        System.out.println("GetRequest onComplete");
                                    }
                                });

                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("interval onSubscribe");
            }

            @Override
            public void onNext(Long value) {
                System.out.println("interval onNext " + value);
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
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {

        }
    }
}
