package com.andryyu.kt.domain

import com.andryyu.kt.net.ForecastRequest

/**
 * Created by yufei on 2018/3/15.
 */
class RequestForecastCommand (private val zipCode: String) :
        Command<ForecastList>{
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
                forecastRequest.execute())
    }
}