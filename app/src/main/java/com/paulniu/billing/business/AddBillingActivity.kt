package com.paulniu.billing.business

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_add_billing.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 6:06 PM
 * desc: 添加账单页面
 */
class AddBillingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_billing)

        initListener()
    }

    private fun initListener() {
        add_bill_activity_back_iv.setOnClickListener {
            onBackPressed()
        }
    }

}