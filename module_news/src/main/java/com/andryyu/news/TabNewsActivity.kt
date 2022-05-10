package com.andryyu.news

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.andryyu.network.observer.HandleSubscriber
import com.andryyu.network.retrofit.RetrofitManager
import com.andryyu.network.rxjava.RxUtils
import com.andryyu.news.pojo.BaseNewsResp
import com.andryyu.news.pojo.entity.NewsChannelResp
import com.andryyu.news.ui.main.SectionsPagerAdapter
import com.andryyu.utils.utils.Toasts
import io.reactivex.disposables.Disposable

class TabNewsActivity : AppCompatActivity() {

    private var apiService:NewService ?= null
    private var sectionsPagerAdapter:SectionsPagerAdapter ?= null
    private var channels:List<NewsChannelResp.ChannelEntity>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_news)

        initView()
        getNewsChannel()
    }

    /**
     * 初始化视图
     */
    private fun initView(){
        channels = mutableListOf()

        sectionsPagerAdapter = SectionsPagerAdapter(channels as MutableList<NewsChannelResp.ChannelEntity>, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    /**
     * 获取新闻类别
     */
    private fun getNewsChannel(){
        apiService = RetrofitManager.getInstance().create(NewService::class.java)
        apiService!!.getNewsChannels()
                .compose(RxUtils.applySchedulersNoLoading())
                .subscribe(object : HandleSubscriber<BaseNewsResp<NewsChannelResp?>?>() {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseNewsResp<NewsChannelResp?>?) {
                        super.onNext(t)
                        if (t!!.isSuccess()){
                            sectionsPagerAdapter!!.setData(t.showapi_res_body!!.channelList!!.subList(1,6))
                            sectionsPagerAdapter!!.notifyDataSetChanged()
                        }else{
                            Toasts.showToast(this@TabNewsActivity,t.showapi_res_error)
                        }
                    }
                })
    }
}