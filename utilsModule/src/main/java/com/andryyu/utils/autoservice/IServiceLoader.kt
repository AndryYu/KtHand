package com.zhuoer.library.autoservice

import java.util.*

object IServiceLoader {
    fun <S> load(service: Class<S>?): S? {
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (e: Exception) {
            null
        }
    }
}