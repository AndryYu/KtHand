package com.android.common.observer

import com.android.common.pojo.BaseFreeApiResp
import io.reactivex.Observer

abstract class HandleSubscriber<T : BaseFreeApiResp<*>?> : Observer<T> {
    override fun onNext(t: T) {
        //添加一些状态拦截
    }

    override fun onError(e: Throwable) {}
    override fun onComplete() {}
}