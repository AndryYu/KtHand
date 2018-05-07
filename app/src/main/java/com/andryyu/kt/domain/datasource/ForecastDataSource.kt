package com.andryyu.kt.domain.datasource

import com.andryyu.kt.domain.Forecast
import com.andryyu.kt.domain.ForecastList

/**
 * Created by KM-ZhangYufei on 2018/3/21.
 */
interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}