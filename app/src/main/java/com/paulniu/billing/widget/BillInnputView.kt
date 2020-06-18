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

    }

    /**
     * 添加文本内容，此处只能是自定义软键盘输入的内容
     */
    fun changeMoney(money: String) {
        if(TextUtils.equals(money,"-") || TextUtils.equals(money,"+") || TextUtils.equals(money,"@")){
            return
        }
        if (TextUtils.equals(money, "删除")) {
            // 删除一个字符
            if (bill_input_view_input_tv.text.length > 1) {
                val len = bill_input_view_input_tv.text.length
                // 删除最后一个字符
                bill_input_view_input_tv.text =
                    bill_input_view_input_tv.text.removeRange(len - 2, len - 1)
            } else {
                bill_input_view_input_tv.text = "0"
            }
        } else if (bill_input_view_input_tv.text.length == 1 && TextUtils.equals(bill_input_view_input_tv.text,"0") && !TextUtils.equals(bill_input_view_input_tv.text,".")) {
            bill_input_view_input_tv.text = money
        } else if (bill_input_view_input_tv.text.contains(".") && TextUtils.equals(money,".")){
            // 不用执行任何操作
        }else {
            val curValue = bill_input_view_input_tv.text.toString()
            bill_input_view_input_tv.text = curValue + money
        }
    }

    /**
     * 通过方法重新设置文本内容
     */
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
        return bill_input_view_input_tv.text.trim().toString()
    }

}