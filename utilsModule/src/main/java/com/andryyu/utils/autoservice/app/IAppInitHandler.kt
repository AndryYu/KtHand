package com.andryyu.utils.autoservice.app

import com.andryyu.utils.application.BaseApplication

interface IAppInitHandler {
    fun onGeneralInit(application: BaseApplication, isMain:Boolean)
    fun onInitPrivacyAgreed(application: BaseApplication, isMain:Boolean)
}