package com.andryyu.network.observer

import io.reactivex.Observer

abstract class HandleSubscriber<T> : Observer<T> {
    override fun onNext(t: T) {
        //添加一些状态拦截
    }

    override fun onError(e: Throwable) {}
    override fun onComplete() {}
}