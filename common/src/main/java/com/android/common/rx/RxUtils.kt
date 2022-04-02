package com.android.common.rx

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * ================================================
 * 放置便于使用 RxJava 的一些工具方法
 *
 *
 * Created by MVPArmsTemplate on 12/12/2018 14:13
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
object RxUtils {
    /* public static <T> ObservableTransformer<T, T> applySchedulers(BaseViewModel viewModel) {
        return observable -> //隐藏进度条
                observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    viewModel.setIsLoading(true);//显示进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally((Action) () -> {
                    viewModel.setIsLoading(false);
                });
    }*/
    fun <T> applySchedulersNoLoading(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applySchedulers(observer: Observer<T>?): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream: Observable<T> ->
            val observable = upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            observable.subscribe(observer!!)
            observable
        }
    }
}