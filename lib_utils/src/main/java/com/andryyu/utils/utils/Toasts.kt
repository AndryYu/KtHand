package com.andryyu.utils.utils

import android.content.Context
import android.widget.Toast

/**
 * 吐司工具类
 */
object Toasts {
    fun showToast(context: Context, log: String?) {
        Toast.makeText(context , log , Toast.LENGTH_SHORT).show()
    }
}