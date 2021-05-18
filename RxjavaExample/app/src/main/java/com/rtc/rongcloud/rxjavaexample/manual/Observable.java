package com.rtc.rongcloud.rxjavaexample.manual;


public abstract class Observable<T> {

    public abstract void subscribe(Observer<T> observer);

    public static <T> Observable<T> create(Observable<T> observable) {
        return observable;
    }

    public Observable<T> nullMap() {

        return new Observable<T>() {
            @Override
            public void subscribe(final Observer<T> observerC) {

                Observer<T> observerB = new Observer<T>() {
                    @Override
                    public void onNext(T t) {
                        observerC.onNext(t);
                    }

                    @Override
                    public void onComplete() {
                        observerC.onComplete();
                    }
                };
                Observable.this.subscribe(observerB);
            }
        };
    }

    public <R> Observable<R> map(final Function<T, R> function) {

        return new Observable<R>() {
            @Override
            public void subscribe(final Observer<R> observer1) {
                Observable.this.subscribe(new Observer<T>() {
                    @Override
                    public void onNext(T t) {
                        R r = function.apply(t); // 仅仅在这里加了变换操作
                        observer1.onNext(r);
                    }

                    @Override
                    public void onComplete() {
                        observer1.onComplete();
                    }
                });
            }
        };
    }

    public <R> Observable<R> flatMap(final Function<T, Observable<R>> function) {

        return new Observable<R>() {
            @Override
            public void subscribe(final Observer<R> observer) {
                Observable.this.subscribe(new Observer<T>() {
                    @Override
                    public void onNext(T t) {
                        try {
                            Observable<R> observable = function.apply(t);
                            observable.subscribe(observer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        };

    }

}
