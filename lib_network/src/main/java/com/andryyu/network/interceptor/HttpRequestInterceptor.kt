package com.andryyu.network.interceptor

import com.andryyu.network.app.NetConstant
import com.andryyu.network.encrpt.EncryptManager.calcAuthorization
import com.andryyu.utils.utils.SPUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class HttpRequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SPUtils["user_token", ""] as String?
        val mUrl = chain.request().url.toString()
        var builder:Request.Builder?=null
        if (mUrl.contains("myqcloud.com")){
            val cd: Calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            val datetime: String = sdf.format(cd.time)
            // 签名
            val auth: String = calcAuthorization(NetConstant.Source, NetConstant.SecretID, NetConstant.SecretKey, datetime)
            builder = chain.request()
                    .newBuilder()
                    .header("X-Source", NetConstant.Source)
                    .header("X-Date", datetime)
                    .header("Authorization", auth)
                    .header("Content-Type", "text/html; charset=UTF-8")
        }else {
            builder= chain.request()
                    .newBuilder()
                    .header("token", token ?: "")
                    .header("Content-Type", "text/html; charset=UTF-8")
        }
        val request: Request = builder.build()
        return chain.proceed(request)
    }

}