package com.android.common.pojo

class BaseFreeApiResp<T> {
    var code = 0
    var msg: String? = null
    var data: T? = null
        private set
    var time: Long = 0
    var log_id: Long = 0
    fun setData(data: T) {
        this.data = data
    }

    fun isSuccess():Boolean{
        return code==200
    }
}