package com.paulniu.billing.business

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_analysis.*
import kotlinx.android.synthetic.main.view_analysis_toolbar.view.*
import kotlinx.android.synthetic.main.view_setting_toolbar.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 6:11 PM
 * desc: 报表分析页面
 */
class AnalysisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)

        initView()
    }


    private fun initView() {
        setSupportActionBar(analysis_activity_toolbar)
        // 设置toolbar的自定义样式
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setBackgroundDrawable(null)
        val analysis_toolbar_layout = layoutInflater.inflate(R.layout.view_analysis_toolbar, null)
        val analysis_toolbar_params = ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        supportActionBar?.setCustomView(analysis_toolbar_layout, analysis_toolbar_params)
        analysis_toolbar_layout.analysis_toolbar_back_iv.setOnClickListener {
            onBackPressed()
        }
    }

}