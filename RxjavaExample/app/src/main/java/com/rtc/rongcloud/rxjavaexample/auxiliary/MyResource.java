package com.rtc.rongcloud.rxjavaexample.auxiliary;

/**
 * 用于在Observable的生命周期内存在的资源对象
 */
class MyResource {
    private String resource;

    public MyResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "MyResource{" +
                "resource='" + resource + '\'' +
                '}';
    }

    public void releaseResource() {
        System.out.println("----> MyResource resource is release. ");
        resource = null;
    }
}