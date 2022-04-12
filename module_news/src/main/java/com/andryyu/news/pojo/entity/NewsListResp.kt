package com.andryyu.news.pojo.entity

class NewsListResp {
    var allPages:Int = 0
    var contentlist:List<NewContentEntity>?=null
    var currentPage:Int = 0
    var allNum:Int = 0
    var maxResult:Int = 0


    class NewContentEntity{
        var allList:List<String>?=null
        var pubDate:String?=null
        var title:String?=null
        var channelName:String?=null
        var imageurls:List<ImageUrlEntity>?=null
        var desc:String?=null
        var source:String ?= null
        var link:String?=null
    }

    class ImageUrlEntity{
        var height:Int = 0
        var width:Int = 0
        var url:String? = null
    }
}