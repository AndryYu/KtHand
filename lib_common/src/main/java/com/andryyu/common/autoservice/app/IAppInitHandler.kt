package com.andryyu.common.autoservice.app

import com.andryyu.common.base.BaseApplication


interface IAppInitHandler {
    fun onGeneralInit(application: BaseApplication, isMain:Boolean)
    fun onInitPrivacyAgreed(application: BaseApplication, isMain:Boolean)
}