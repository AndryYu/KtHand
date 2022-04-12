package com.andryyu.network.retrofit;


import com.andryyu.network.interceptor.HttpRequestInterceptor;
import com.andryyu.network.interceptor.logging.Level;
import com.andryyu.network.interceptor.logging.LoggingInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.andryyu.network.BuildConfig;

public class RetrofitManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private final Retrofit mRetrofit;

    private RetrofitManager(){
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

        //添加请求拦截
        builder.addInterceptor(new HttpRequestInterceptor());
        //打印log日志
        builder.addInterceptor(new LoggingInterceptor
                .Builder()//构建者模式
                .loggable(BuildConfig.DEBUG) //是否开启日志打印
                .setLevel(Level.BASIC) //打印的等级
                .log(Platform.INFO) // 打印类型
                .request("LOG_Request") // request的Tag
                .response("LOG_Response")// Response的Tag
                .build());
        trustAllCertOkHttp(builder);
        builder.hostnameVerifier((String hostname, SSLSession session) -> true);

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static RetrofitManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }


    /**
     * 不对证书做校验
     *
     * @param builder
     */
    private void trustAllCertOkHttp(OkHttpClient.Builder builder) {
        try {
            // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
            final X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            };

            final SSLSocketFactory sslSocketFactory = new SSLSocketFactoryCompat(x509TrustManager);
            builder.sslSocketFactory(sslSocketFactory, x509TrustManager);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
