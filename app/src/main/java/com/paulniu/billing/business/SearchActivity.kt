package com.paulniu.billing.business

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_search.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 6:27 PM
 * desc: 搜索账单页面
 */
class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initListener()
    }

    private fun initListener() {
        search_activity_back_iv.setOnClickListener {
            onBackPressed()
        }
    }

}