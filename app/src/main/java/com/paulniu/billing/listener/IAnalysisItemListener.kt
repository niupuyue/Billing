package com.paulniu.billing.listener

import com.paulniu.bill_data_lib.bean.TypeInfo

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/13 5:48 PM
 * desc: 分析页面点击 单独的一个item
 */
interface IAnalysisItemListener {

    fun onTypeItemClick(type:TypeInfo)

}