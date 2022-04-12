package com.andryyu.utils.utils

import android.content.Context
import com.andryyu.utils.Utils.Companion.app

/**
 * Created by Jay on 2015/9/2 0002.
 */
object SPUtils {
    // 保存在手机里的SP文件名
    private const val FILE_NAME = "UserSharePreference"

    // 卸载删除文件
    private const val FILE_NOT_DELETE_NAME = "file_not_detele_name"

    /**
     *
     * put
     * 保存数据
     */
    fun put(key: String?, obj: Any?) {
        val context = app!!.applicationContext
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when (obj) {
            is Boolean -> {
                editor.putBoolean(key, (obj as Boolean?)!!)
            }
            is Float -> {
                editor.putFloat(key, (obj as Float?)!!)
            }
            is Int -> {
                editor.putInt(key, (obj as Int?)!!)
            }
            is Long -> {
                editor.putLong(key, (obj as Long?)!!)
            }
            else -> {
                editor.putString(key, obj as String?)
            }
        }
        editor.apply()
    }

    /**
     *
     * putEx
     * 保存数据(一直保存，除非删除APP)
     */
    fun putEx(key: String?, obj: Any?) {
        val context = app!!.applicationContext
        val sp = context.getSharedPreferences(FILE_NOT_DELETE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when (obj) {
            is Boolean -> {
                editor.putBoolean(key, (obj as Boolean?)!!)
            }
            is Float -> {
                editor.putFloat(key, (obj as Float?)!!)
            }
            is Int -> {
                editor.putInt(key, (obj as Int?)!!)
            }
            is Long -> {
                editor.putLong(key, (obj as Long?)!!)
            }
            else -> {
                editor.putString(key, obj as String?)
            }
        }
        editor.apply()
    }

    /**
     *
     * get
     * 获取指定数据
     */
    operator fun get(key: String?, defaultObj: Any?): Any? {
        val context = app!!.applicationContext
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        when (defaultObj) {
            is Boolean -> {
                return sp.getBoolean(key, (defaultObj as Boolean?)!!)
            }
            is Float -> {
                return sp.getFloat(key, (defaultObj as Float?)!!)
            }
            is Int -> {
                return sp.getInt(key, (defaultObj as Int?)!!)
            }
            is Long -> {
                return sp.getLong(key, (defaultObj as Long?)!!)
            }
            is String -> {
                return sp.getString(key, defaultObj as String?)
            }
            else -> return null
        }
    }

    /**
     *
     * getEx
     * 获取指定数据(一直保存，除非删除APP)
     */
    fun getEx(key: String?, defaultObj: Any?): Any? {
        val context = app!!.applicationContext
        val sp = context.getSharedPreferences(FILE_NOT_DELETE_NAME, Context.MODE_PRIVATE)
        when (defaultObj) {
            is Boolean -> {
                return sp.getBoolean(key, (defaultObj as Boolean?)!!)
            }
            is Float -> {
                return sp.getFloat(key, (defaultObj as Float?)!!)
            }
            is Int -> {
                return sp.getInt(key, (defaultObj as Int?)!!)
            }
            is Long -> {
                return sp.getLong(key, (defaultObj as Long?)!!)
            }
            is String -> {
                return sp.getString(key, defaultObj as String?)
            }
            else -> return null
        }
    }

    /**
     *
     * remove
     * 删除指定数据
     */
    fun remove(context: Context, key: String?) {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        editor.apply()
    }

    /**
     *
     * getAll
     * @Description 返回所有键值对
     */
    fun getAll(context: Context): Map<String, *> {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sp.all
    }

    /**
     *
     * clear
     * @Description 删除所有数据
     */
    fun clear(context: Context) {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }

    /**
     *
     * contains
     * @Description 检查key对应的数据是否存在
     */
    fun contains(context: Context, key: String?): Boolean {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sp.contains(key)
    }
}