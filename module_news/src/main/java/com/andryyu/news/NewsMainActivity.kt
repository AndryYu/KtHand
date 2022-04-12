package com.andryyu.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andryyu.network.retrofit.RetrofitManager

class NewsMainActivity : AppCompatActivity() {
    private var apiService: NewService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        apiService = RetrofitManager.getInstance().create(NewService::class.java)
    }
}