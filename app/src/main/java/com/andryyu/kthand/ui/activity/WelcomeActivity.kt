package com.andryyu.kthand.ui.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.andryyu.common.base.BaseActivity
import com.andryyu.kthand.R

class WelcomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        findViewById<Button>(R.id.btn_load).setOnClickListener{
            ARouter.getInstance().build("/app/MainActivity").navigation()
        }
    }
}