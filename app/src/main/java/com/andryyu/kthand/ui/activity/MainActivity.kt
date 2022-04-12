package com.andryyu.kthand.ui.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.andryyu.common.base.BaseActivity
import com.andryyu.kthand.R
import com.andryyu.network.retrofit.RetrofitManager

@Route(path = "/app/MainActivity")
class MainActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_sample_1).setOnClickListener{
            ARouter.getInstance().build("/home/HomeActivity").navigation()
        }
        findViewById<Button>(R.id.btn_sample_2).setOnClickListener {
            ARouter.getInstance().build("/day/DayMainActivity").navigation()
        }
    }
}
