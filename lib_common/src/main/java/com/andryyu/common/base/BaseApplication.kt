package com.andryyu.common.base

import android.app.Application
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.android.common.BuildConfig
import com.andryyu.utils.Utils
import com.andryyu.common.autoservice.app.IAppInitHandler
import com.andryyu.utils.utils.crash.AppCrashHandler
import java.util.*
import kotlin.collections.ArrayList

open class BaseApplication: Application() {
    var handlerList:MutableList<IAppInitHandler> = ArrayList()

    private val mApplicationId: String? = null

    init {
        val serviceLoader = ServiceLoader.load(
                IAppInitHandler::class.java
        )
        for (handler in serviceLoader) {
            handlerList.add(handler)
        }
    }


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Utils.init(this)
        ARouter.init(this)
        onDebugConfig()
    }

    /**
     * 调试模式下配置
     */
    private fun onDebugConfig(){
        if (BuildConfig.DEBUG){
            //开启ARouter日志
            ARouter.openDebug()
            ARouter.openLog()

            //保存崩溃日志
            AppCrashHandler.getInstance().setCrashHandler(this)
        }
    }


    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}