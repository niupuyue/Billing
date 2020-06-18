package com.paulniu.billing.listener

import com.paulniu.billing.adapter.SoftKeyboardAdapter

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 2:50 PM
 * desc: 自定义软键盘adapter的点击事件
 */
interface ISoftKeyboardSelectListener {

    fun onSelectButton(value:SoftKeyboardAdapter.KeyboardData)

}