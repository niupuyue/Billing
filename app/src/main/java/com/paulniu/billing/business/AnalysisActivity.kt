package com.paulniu.billing.business

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_analysis.*

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

        initListener()
    }


    private fun initListener() {
        analysis_activity_back_iv.setOnClickListener {
            onBackPressed()
        }
    }

}