package com.andryyu.kt.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.andryyu.kt.R
import com.andryyu.kt.base.BaseActivity
import com.andryyu.kt.domain.RequestForecastCommand
import com.andryyu.kt.extensions.showToast
import com.andryyu.kt.ui.adapter.ForecastListAdapter
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class WelcomeActivity : BaseActivity() {

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val forecastList:RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        //forecastList.adapter = ForecastListAdapter(items)

        async() {
            val result = RequestForecastCommand("94043").execute()
            uiThread{
                forecastList.adapter =ForecastListAdapter(result) { toast(it.date) }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showToast("这是一个extension扩展函数")
    }

    public class Request(val url: String) {
        public fun run() {
            val forecastJsonStr = URL(url).readText()
            Log.d(javaClass.simpleName, forecastJsonStr)
        }
    }


}
