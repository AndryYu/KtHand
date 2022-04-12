package com.andryyu.onewen

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import com.alibaba.android.arouter.facade.annotation.Route
import com.andryyu.common.base.BaseActivity
import com.andryyu.network.observer.HandleSubscriber
import com.andryyu.onewen.pojo.BaseFreeApiResp
import com.andryyu.network.rxjava.RxUtils
import com.andryyu.network.retrofit.RetrofitManager
import com.andryyu.onewen.module.DayService
import com.andryyu.onewen.pojo.entity.OneWenEntity
import com.andryyu.utils.utils.Toasts
import io.reactivex.disposables.Disposable

@Route(path = "/day/DayMainActivity")
class DayMainActivity : BaseActivity() {

    private var apiService: DayService?=null
    private var wv:WebView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_wen)

        wv = findViewById(R.id.wv_day)
        apiService = RetrofitManager.getInstance().create(DayService::class.java)
        getOneWen()
    }

    /**
     * 获取每日一文
     */
    private fun getOneWen(){
        apiService!!.getOneWen("2022-04-04")!!
                .compose(RxUtils.applySchedulersNoLoading())
                .subscribe(object : HandleSubscriber<BaseFreeApiResp<OneWenEntity?>?>() {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: BaseFreeApiResp<OneWenEntity?>?) {
                        super.onNext(t)
                        if (t!!.isSuccess()) {
                            Log.i("zyf",t.data!!.content.toString())

                            wv!!.loadData(t.data!!.content.toString(),"text/html","UTF-8")
                        } else {
                            Toasts.showToast(this@DayMainActivity,t.msg)
                        }
                    }
                })
    }
}