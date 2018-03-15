package com.andryyu.kt.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by KM-ZhangYufei on 2018/3/15.
 */
// 这个函数不需要传入任何context，可以被任何context或者它的子类调用，比如Activity或者Service
fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(this, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}