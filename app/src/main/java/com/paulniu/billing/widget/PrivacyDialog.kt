package com.paulniu.billing.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import com.paulniu.bill_base_lib.listener.ITextViewClickListener
import com.paulniu.bill_base_lib.util.SPUtil
import com.paulniu.bill_base_lib.util.StringControlUtil
import com.paulniu.billing.Constant
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.view_dialog_privacy.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/5 8:34 PM
 * desc: 用户首次使用的隐私权政策弹窗
 */
class PrivacyDialog constructor(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_dialog_privacy)

        // 设置弹窗不可关闭
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        // 设置弹窗样式
        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        privacy_dialog_agree_tv.setOnClickListener {
            // 点击同意按钮
            if (isShowing) {
                dismiss()
            }
            // 更改SP
            SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)
                ?.put(Constant.SP_KEY_SHOW_PRIVACY_DIALOG, true)
        }

        // 设置用户隐私政策的点击事件
        val hotWords = arrayOf("《服务协议》", "《隐私协议》")
        StringControlUtil.setHotWordsText(context,
            privacy_dialog_content_tv,
            context.resources.getString(R.string.privacy_dialog_content),
            hotWords,
            R.color.color_53B3F4,
            false,
            object : ITextViewClickListener {
                override fun onSpanClick(position: Int) {
                    // 点击了文字
                    when (position) {
                        0 -> {
                            // 跳转到服务协议页面
                        }
                        1 -> {
                            // 跳转到隐私协议网页
                        }
                    }
                }

            })
    }

}