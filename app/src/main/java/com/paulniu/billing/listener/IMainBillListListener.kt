package com.paulniu.billing.listener

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/8 12:35 PM
 * desc: 首页账单列表事件回调
 */
interface IMainBillListListener {
    fun onClick(position: Int)

    fun onLongClick(position: Int)
}