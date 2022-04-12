package com.andryyu.news.pojo

class BaseNewsResp<T> {
    var showapi_res_code:Int =0
    var showapi_res_error:String?=null
    var showapi_res_body:T? = null
        private set

    fun setData(data: T) {
        this.showapi_res_body = data
    }

    fun isSuccess():Boolean{
        return showapi_res_code==0
    }
}