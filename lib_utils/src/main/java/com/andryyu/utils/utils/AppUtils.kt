package com.andryyu.utils.utils

import android.content.Context
import android.content.pm.PackageManager

object AppUtils {

    @Synchronized
    fun getAppName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getVersionCode(mContext: Context): String? {
        var versionCode: String? = ""
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode =
                mContext.packageManager.getPackageInfo(mContext.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionCode
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 0代表相等，1代表version1大于version2，-1代表version1小于version2
     * @param version1
     * @param version2
     * @return
     */
    fun compareVersion(version1: String, version2: String): Int {
        if (version1 == version2) {
            return 0
        }
        val version1Array = version1.split("\\.".toRegex()).toTypedArray()
        val version2Array = version2.split("\\.".toRegex()).toTypedArray()
        var index = 0
        // 获取最小长度值
        val minLen = Math.min(version1Array.size, version2Array.size)
        var diff = 0
        // 循环判断每位的大小
        while (index < minLen
            && version1Array[index].toInt() - version2Array[index].toInt().also { diff = it } == 0
        ) {
            index++
        }
        return if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (i in index until version1Array.size) {
                if (version1Array[i].toInt() > 0) {
                    return 1
                }
            }
            for (i in index until version2Array.size) {
                if (version2Array[i].toInt() > 0) {
                    return -1
                }
            }
            0
        } else {
            if (diff > 0) 1 else -1
        }
    }
}