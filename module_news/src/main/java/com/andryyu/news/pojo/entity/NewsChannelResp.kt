package com.andryyu.news.pojo.entity

class NewsChannelResp {
    var totalNum:Int = 0
    var ret_code:Int = 0
    var channelList:List<ChannelEntity>?=null

    class ChannelEntity{
        var channelId:String? = null
        var name:String? = null

    }
}