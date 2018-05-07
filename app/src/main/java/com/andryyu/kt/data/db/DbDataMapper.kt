package com.andryyu.kt.data.db

import com.andryyu.kt.domain.Forecast
import com.andryyu.kt.domain.ForecastList

/**
 * Created by KM-ZhangYufei on 2018/3/21.
 */
class DbDataMapper {

    fun convertFromDomain(forecast: ForecastList) = with(forecast){
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id,city,country,daily)
    }

    private fun convertDayFromDomain(cityId:Long, forecast:Forecast)= with(forecast){
        DayForecast(date,description,high,low,iconUrl,cityId);
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast){
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id,city,country,daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast){
        Forecast(_id,date,description,high,low,iconUrl);
    }
}