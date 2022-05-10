package com.andryyu.news.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.andryyu.news.pojo.entity.NewsChannelResp

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(var channelList:List<NewsChannelResp.ChannelEntity>, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {


    fun setData(datas:List<NewsChannelResp.ChannelEntity>){
        this.channelList = datas
    }

    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(channelList[position].channelId!!)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return channelList[position].name
    }

    override fun getCount(): Int {
        return channelList!!.size
    }
}