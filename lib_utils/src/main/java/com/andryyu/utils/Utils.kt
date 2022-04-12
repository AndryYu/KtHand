package com.andryyu.utils

import android.annotation.SuppressLint
import android.app.Application
import java.lang.NullPointerException
import java.lang.UnsupportedOperationException
import java.lang.reflect.InvocationTargetException

class Utils private constructor() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var sApplication: Application? = null

        /**
         * Init utils.
         *
         * Init it in the class of Application.
         *
         * @param app application
         */
        fun init(app: Application) {
            if (sApplication == null) {
                sApplication = app
            }
        }

        /**
         * Return the context of Application object.
         *
         * @return the context of Application object
         */
        val app: Application?
            get() {
                if (sApplication != null) {
                    return sApplication
                }
                try {
                    @SuppressLint("PrivateApi") val activityThread =
                        Class.forName("android.app.ActivityThread")
                    val at = activityThread.getMethod("currentActivityThread").invoke(null)
                    val app = activityThread.getMethod("getApplication").invoke(at)
                        ?: throw NullPointerException("u should init first")
                    init(app as Application)
                    return sApplication
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }
                throw NullPointerException("u should init first")
            }
    }

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
}