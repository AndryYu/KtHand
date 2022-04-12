package com.andryyu.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class BannerHomeAdapter(var activity:AppCompatActivity, datas:List<String>): BannerImageAdapter<String>(datas) {

    companion object {
        val options = RequestOptions.centerInsideTransform()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    }
    override fun onBindView(holder: BannerImageHolder?, data: String?, position: Int, size: Int) {
        Glide.with(activity)
                .load(data)
                .apply(options)
                .transition(DrawableTransitionOptions().dontTransition())
                .into(holder!!.imageView)
    }
}