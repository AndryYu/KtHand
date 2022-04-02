package com.andryyu.kthand.service

import com.android.common.pojo.BaseFreeApiResp
import com.andryyu.kthand.model.entity.OneWenEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    /**
     * 发送短信验证码
     */
    @GET("https://v2.alapi.cn/api/one?token=LwExDtUWhF3rH5ib")
    fun getOneWen(@Query("date") date: String?): Observable<BaseFreeApiResp<OneWenEntity?>?>?


}