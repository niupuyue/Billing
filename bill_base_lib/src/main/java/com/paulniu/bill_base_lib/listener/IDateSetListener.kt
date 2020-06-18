package com.paulniu.bill_base_lib.listener

import android.widget.DatePicker

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/15 5:26 PM
 * desc: 时间选择弹窗的选择时间回调
 */
interface IDateSetListener {

    fun onDateSet(startDatePicker: DatePicker,startYear:Int,startMonth:Int,startDay:Int)

}