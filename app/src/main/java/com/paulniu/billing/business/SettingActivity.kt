package com.paulniu.billing.business

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.BuildConfig
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.view_setting_toolbar.view.*

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

        initView()

        initData()
    }

    @SuppressLint("InflateParams")
    private fun initView() {
        setSupportActionBar(setting_activity_toolbar)
        // 设置toolbar的自定义样式
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setBackgroundDrawable(null)
        val settingToolbarLayout = layoutInflater.inflate(R.layout.view_setting_toolbar, null)
        val settingToolbarParams = ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        supportActionBar?.setCustomView(settingToolbarLayout, settingToolbarParams)
        settingToolbarLayout.setting_toolbar_back_iv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initData() {
        setting_activity_app_version_tv.text = BuildConfig.VERSION_NAME
    }

}