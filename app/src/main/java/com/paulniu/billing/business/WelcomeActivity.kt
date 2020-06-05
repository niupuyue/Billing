package com.paulniu.billing.business

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.activity_welcome.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 4:22 PM
 * desc: 欢迎页面
 */
class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initData()
    }

    private fun initData() {
        // 文字动画实现
        val loadText = arrayOf(
            "记       ",
            "记录      ",
            "记录生     ",
            "记录生活    ",
            "记录生活每   ",
            "记录生活每一  ",
            "记录生活每一天 ",
            "记录生活每一天~"
        )
        val textAnimator = ValueAnimator.ofInt(0, 7).setDuration(3000)
        textAnimator.addUpdateListener { animation ->
            val index = animation.animatedValue as Int
            Log.e("NPL", "下标为${index}")
            welcome_activity_center_title_tv.text = loadText[index % loadText.size]
        }
        textAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                Log.e("NPL", "动画执行结束")
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        textAnimator.repeatCount = 0
        textAnimator.start()
    }

}