package com.paulniu.billing.business

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.bill_base_lib.util.SPUtil
import com.paulniu.billing.Constant
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

        initData()

        initListener()
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

    private fun initData() {
        // 从SP中获取用户信息
        val oldName = SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)
            ?.getString(Constant.SP_KEY_USER_BASE_NAME, "牛爱英")
        // 设置当前用户的name
        title_name.text = oldName

        person_activity_username_tv.text = oldName

        person_activity_motto_tv.text = SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)
            ?.getString(Constant.SP_KEY_USER_MOTTO, "好懒啊，什么也没有留下~")

    }

    private fun initListener() {
        person_activity_motto_tv.setOnClickListener {
            // 点击编辑签名
        }

        person_activity_username_tv.setOnClickListener {
            // 点击编辑昵称
        }

        title_name.setOnClickListener {
            // 编辑昵称
        }
    }

}