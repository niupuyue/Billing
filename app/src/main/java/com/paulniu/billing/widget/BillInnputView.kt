package com.paulniu.billing.widget

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.view_bill_input.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 7:40 PM
 * desc: 输入框自定义view
 */
class BillInnputView constructor(context: Context, attrs: AttributeSet) :
    RelativeLayout(context, attrs) {

    init {
        // 引入布局
        LayoutInflater.from(context).inflate(R.layout.view_bill_input, this, true)

        focuseHideSoftKeyboard()

        // 设置输入框监听事件
        bill_input_view_input_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(char: CharSequence?, start: Int, before: Int, count: Int) {
                // 输入框内容改变
                if (TextUtils.isEmpty(char)) {
                    bill_input_view_input_et.setText("0")
                } else if (TextUtils.equals(char?.first().toString(), "0") &&
                    !char?.contains(".")!! && char.length != 1
                ) {
                    // 如果内容以0开始并且没有小数点，则输入无效
                    bill_input_view_input_et.setText(char.removeRange(0, 1))
                }
                // 将光标移到末尾
                bill_input_view_input_et.setSelection(bill_input_view_input_et.text.length)
            }
        })

        // 输入框获取焦点
        bill_input_view_input_et.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                bill_input_view_input_et.setSelection(bill_input_view_input_et.text.toString().length)
            }
        }

        bill_input_view_container_rl.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                // 当总布局获取到触摸事件时，输入框获取焦点
                if (event?.action == MotionEvent.ACTION_UP) {
                    bill_input_view_input_et.requestFocus()
                    bill_input_view_input_et.setSelection(bill_input_view_input_et.text.toString().length)
                }
                return true
            }
        })
    }

    /**
     * 强制隐藏输入框的软键盘
     */
    private fun focuseHideSoftKeyboard() {
        val imm: InputMethodManager? = getSystemService(context, InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(bill_input_view_input_et.windowToken, 0)
    }

    /**
     * 强制将输入框的光标移到行末
     */
    private fun foucseEndFouse() {

    }

    // 通过方法重新设置文本内容
    fun setBillTitle(iconRes: Int?, title: String?) {
        if (!TextUtils.isEmpty(title)) {
            bill_input_view_title_tv.text = title
        }
        if (null != iconRes && iconRes > 0) {
            bill_input_view_icon_iv.background = ContextCompat.getDrawable(context, iconRes!!)
        }
    }

    /**
     * 获取输入框的内容
     * 输入框的内容是0，则返回null
     */
    fun getBillMoney(): String? {
        return bill_input_view_input_et.text.trim().toString()
    }

}