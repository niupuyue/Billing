package com.paulniu.billing.business

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_person.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 6:12 PM
 * desc: 个人信息编辑页面
 */
class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        initListener()
    }

    private fun initListener() {
        person_activity_back_iv.setOnClickListener {
            onBackPressed()
        }
    }

}