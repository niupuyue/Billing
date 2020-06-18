package com.paulniu.billing.listener

import com.paulniu.bill_data_lib.bean.BillNoteBean

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 3:55 PM
 * desc: 添加账单备注弹窗
 */
interface IAddBillNoteListener {

    fun onAddBillNote(billNote: BillNoteBean)

}