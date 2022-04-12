package com.andryyu.home

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.andryyu.common.base.BaseActivity
import com.youth.banner.Banner
import com.youth.banner.indicator.RectangleIndicator

@Route(path="/home/HomeActivity")
class HomeActivity : BaseActivity() {

    var banner:Banner<String,BannerHomeAdapter>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val imageList = mutableListOf("https://api.oick.cn/random/api.php",
                "https://api.oick.cn/random/pc/466f79e8ly1fw5oh8lh33j218g0p011c.jpg",
                "https://api.oick.cn/random/api.php")
        imageList.add("https://api.oick.cn/random/pc/466f79e8ly1fw5oi9coj2j20nm0go15j.jpg")

        banner = findViewById(R.id.banner_home)

        val adapter = BannerHomeAdapter(this@HomeActivity,imageList)
        banner!!.setAdapter(adapter)
        .addBannerLifecycleObserver(this).indicator = RectangleIndicator(this)

    }
}


