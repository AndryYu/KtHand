package com.andryyu.utils.application

import android.app.Application
import com.andryyu.utils.Utils
import com.andryyu.utils.autoservice.app.IAppInitHandler
import java.util.*
import kotlin.collections.ArrayList

open class BaseApplication : Application() {
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
        Utils.init(this)
    }
}