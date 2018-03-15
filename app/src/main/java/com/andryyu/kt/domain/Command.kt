package com.andryyu.kt.domain

/**
 * Created by yufei on 2018/3/15.
 */
interface Command<T> {
    fun execute(): T
}