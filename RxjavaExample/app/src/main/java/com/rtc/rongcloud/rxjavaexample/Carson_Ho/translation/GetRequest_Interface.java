package com.rtc.rongcloud.rxjavaexample.Carson_Ho.translation;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetRequest_Interface {

    @GET("dictionary.php?w=go&key=********")
    Observable<Translation> getCall();
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // 采用Observable<...>接口
    // getCall()是接受网络请求数据的方法
}
