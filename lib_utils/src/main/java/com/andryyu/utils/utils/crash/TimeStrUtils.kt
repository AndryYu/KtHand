package com.andryyu.utils.utils.crash

import java.text.SimpleDateFormat
import java.util.*

object TimeStrUtils {
    private val HEX_DIGITS = "0123456789ABCDEF".toCharArray()

    /**
     *
     * long2HexString
     *
     * @param n
     * @param byteNum
     * @return
     */
    fun long2HexString(n: Long, byteNum: Int): String {
        var n = n
        var byteNum = byteNum
        byteNum = byteNum shl 1
        val buf = CharArray(byteNum)
        for (i in byteNum - 1 downTo 0) {
            buf[i] = HEX_DIGITS[(n and 15L).toInt()]
            n = n ushr 4
        }
        return String(buf)
    }

    /**
     *
     * getUTCMills
     *
     * @return 获取UTC（0时区）时间1970年1月1日00:00:00到当前的秒数
     */
    fun getUTCMills(currentTime: Date): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var interval: Long = 0
        //将截取到的时间字符串转化为时间格式的字符串 
        try {
            val beginTime = sdf.parse("1970-01-01 00:00:00")
            //默认为毫秒 
            interval = (currentTime.time - beginTime.time) / 1000
            println(interval)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return interval
    }

    val DEFAULT_SDF = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val DEFAULT_DAY_SDF = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    /**
     *
     * getCurTimeString
     * @Description：   获取当前时间
     * @return 时间字符串
     */
    @JvmStatic
    val curTimeString: String
        get() = date2String(Date())

    /**
     *
     * date2String
     * @param time Date类型时间
     * @return 时间字符串
     * @Description:        将Date类型转化成格式为yyyy-MM-dd HH:mm:ss<
     */
    @JvmStatic
    fun dateDay2String(time: Date?): String {
        return date2String(time, DEFAULT_DAY_SDF)
    }
    /**
     *
     * date2String
     * @param time   Date类型时间
     * @param format 时间格式
     * @Description:        将Date类型转为时间字符串
     * @return 时间字符串
     */
    /**
     *
     * date2String
     * @param time Date类型时间
     * @return 时间字符串
     * @Description:        将Date类型转化成格式为yyyy-MM-dd HH:mm:ss<
     */
    @JvmOverloads
    fun date2String(time: Date?, format: SimpleDateFormat = DEFAULT_SDF): String {
        return format.format(time)
    }
}