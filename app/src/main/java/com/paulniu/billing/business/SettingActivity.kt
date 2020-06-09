package com.paulniu.billing.business

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 6:10 PM
 * desc: 设置页面
 */
class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initListener()
    }

    private fun initListener() {
        setting_activity_back_iv.setOnClickListener {
            onBackPressed()
        }
    }

}