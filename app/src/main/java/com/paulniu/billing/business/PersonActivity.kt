package com.paulniu.billing.business

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_person.*
import kotlinx.android.synthetic.main.view_analysis_toolbar.view.*
import kotlinx.android.synthetic.main.view_person_toolbar.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 6:12 PM
 * desc: 个人信息编辑页面
 */
class PersonActivity : AppCompatActivity() {

    private lateinit var title_name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        initView()
    }

    private fun initView() {
        setSupportActionBar(person_activity_toolbar)
        // 设置toolbar的自定义样式
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setBackgroundDrawable(null)
        val person_toolbar_layout = layoutInflater.inflate(R.layout.view_person_toolbar, null)
        val person_toolbar_params = ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        supportActionBar?.setCustomView(person_toolbar_layout, person_toolbar_params)
        person_toolbar_layout.person_toolbar_back_iv.setOnClickListener {
            onBackPressed()
        }
        title_name = person_toolbar_layout.person_toolbar_title_tv
    }

}