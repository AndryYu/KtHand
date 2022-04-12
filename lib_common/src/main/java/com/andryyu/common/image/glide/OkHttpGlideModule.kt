package com.andryyu.common.image.glide

import android.content.Context
import com.andryyu.network.retrofit.SSLSocketFactoryCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

@GlideModule
class OkHttpGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        // 创建 OKHttpClient
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS) //连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS) //写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS) //读操作
        trustAllCertOkHttp(builder)
        builder.hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true })
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpsUrlLoader.Factory(builder.build()))
    }

    /**
     * 不对证书做校验
     *
     * @param builder
     */
    private fun trustAllCertOkHttp(builder: OkHttpClient.Builder) {
        try {
            // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
            val x509TrustManager: X509TrustManager = object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
            val sslSocketFactory: SSLSocketFactory = SSLSocketFactoryCompat(x509TrustManager)
            builder.sslSocketFactory(sslSocketFactory, x509TrustManager)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    companion object {
        private const val DEFAULT_TIME_OUT = 5 //超时时间 5s
        private const val DEFAULT_READ_TIME_OUT = 10
    }
}