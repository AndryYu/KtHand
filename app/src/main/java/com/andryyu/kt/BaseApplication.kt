package com.andryyu.kt

import android.app.Application
import com.andryyu.kt.extensions.DelegatesExt

/**
 * Created by KM-ZhangYufei on 2018/3/15.
 */
class BaseApplication:Application() {

    companion object {
        var instance: BaseApplication by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}