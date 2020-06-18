package com.paulniu.billing.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.paulniu.billing.R
import com.paulniu.billing.adapter.SoftKeyboardAdapter
import com.paulniu.billing.listener.ISoftKeyboardListener
import com.paulniu.billing.listener.ISoftKeyboardSelectListener
import kotlinx.android.synthetic.main.soft_keyboard_view.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 2:42 PM
 * desc: 自定义键盘view
 */
class SoftKeyboardSelectView(context: Context, attr: AttributeSet) : LinearLayout(context, attr),
    ISoftKeyboardSelectListener {

    private var mListener: ISoftKeyboardListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.soft_keyboard_view, this, true)

        initView()
    }

    private fun initView() {
        soft_keyboard_view_rv.layoutManager = GridLayoutManager(context, 4)
        soft_keyboard_view_rv.adapter = SoftKeyboardAdapter(context, this)
    }

    override fun onSelectButton(value: SoftKeyboardAdapter.KeyboardData) {
        // 点击软键盘
        mListener?.onSelect(value)
    }

    fun setSelectKeyboardListener(listener: ISoftKeyboardListener) {
        mListener = listener
    }

}