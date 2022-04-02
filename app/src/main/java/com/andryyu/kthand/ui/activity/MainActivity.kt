package com.andryyu.kthand.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.common.observer.HandleSubscriber
import com.android.common.pojo.BaseFreeApiResp
import com.android.common.rx.RxUtils
import com.andryyu.kthand.R
import com.andryyu.kthand.model.entity.OneWenEntity
import com.andryyu.kthand.service.ApiService
import com.andryyu.network.retrofit.RetrofitManager
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
    private var apiService:ApiService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = RetrofitManager.getInstance().create(ApiService::class.java)
        findViewById<Button>(R.id.btn_sample_2).setOnClickListener {
            getOneWen();
        }
    }

    fun getOneWen(){
        apiService!!.getOneWen("")!!
                .compose(RxUtils.applySchedulersNoLoading())
                .subscribe(object : HandleSubscriber<BaseFreeApiResp<OneWenEntity?>?>() {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: BaseFreeApiResp<OneWenEntity?>?) {
                        super.onNext(t)
                        if (t!!.isSuccess()) {
                            Log.i("zyf",t.data.toString())
                        } else {

                        }
                    }
                })
    }
}
