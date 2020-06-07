package com.paulniu.billing.business

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.bill_base_lib.util.SPUtil
import com.paulniu.billing.Constant
import com.paulniu.billing.R
import com.paulniu.billing.listener.IPrivacyDialogListener
import com.paulniu.billing.widget.PrivacyDialog
import kotlinx.android.synthetic.main.activity_welcome.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 4:22 PM
 * desc: 欢迎页面
 */
class WelcomeActivity : AppCompatActivity() {

    private var isStop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initData()
    }

    override fun onStop() {
        // 执行onStop方法时，不再执行跳转操作
        isStop = true
        super.onStop()
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
                if (!isStop) {
                    startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                    finish()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        textAnimator.repeatCount = 0
        // 判断用户是否是第一次启动应用，如果是第一次启动应用，则先显示弹窗，然后再开启动画
        if (!SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)
                ?.getBoolean(Constant.SP_KEY_SHOW_PRIVACY_DIALOG, false)!!
        ) {
            val privacyDialog = PrivacyDialog(this)
            privacyDialog.setPrivacyDialogListener(object : IPrivacyDialogListener {
                override fun onAgree() {
                    textAnimator.start()
                }

                override fun onDisagree() {
                    finish()
                }
            })
            privacyDialog.show()
        } else {
            textAnimator.start()
        }
    }

}