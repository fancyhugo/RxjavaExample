package com.rtc.rongcloud.java.atomicreference;


import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static void main(String[] args) {
        //3、如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
        SimpleObject test = new SimpleObject("test3", 30);
        AtomicReference<SimpleObject> atomicReference2 = new AtomicReference<>(test);
        Boolean bool = atomicReference2.compareAndSet(test, new SimpleObject("test4", 40));
        System.out.println("simpleObject  Value: " + bool);
    }
}



