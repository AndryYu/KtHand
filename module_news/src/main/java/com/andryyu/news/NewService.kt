package com.andryyu.news

import com.andryyu.news.pojo.BaseNewsResp
import com.andryyu.news.pojo.entity.NewsChannelResp
import com.andryyu.news.pojo.entity.NewsListResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewService {
    @GET("https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/release/news")
    fun getNewsList(@Query("channelId") channelId:String,
                     @Query("channelName") channelName:String,
                     @Query("page") page:String):Observable<BaseNewsResp<NewsListResp>>

    @GET("https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/release/channel")
    fun getNewsChannels():Observable<BaseNewsResp<NewsChannelResp?>?>
}