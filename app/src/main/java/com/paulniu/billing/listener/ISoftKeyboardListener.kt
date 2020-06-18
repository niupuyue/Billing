package com.paulniu.billing.listener

import com.paulniu.billing.adapter.SoftKeyboardAdapter

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 3:15 PM
 * desc: 软键盘点击事件
 */
interface ISoftKeyboardListener {

    fun onSelect(value:SoftKeyboardAdapter.KeyboardData)

}