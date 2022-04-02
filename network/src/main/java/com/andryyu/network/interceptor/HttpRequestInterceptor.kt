package com.andryyu.network.interceptor

import com.andryyu.utils.utils.SPUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HttpRequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SPUtils["user_token", ""] as String?
        val builder: Request.Builder = chain.request()
            .newBuilder()
            .header("token", token ?: "")
            .header("Content-Type", "text/html; charset=UTF-8")
        val request: Request = builder.build()
        return chain.proceed(request)
    }

}