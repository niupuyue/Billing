package com.paulniu.bill_data_lib.source

import com.paulniu.bill_base_lib.base.App
import com.paulniu.bill_data_lib.bean.BillNoteBean
import com.paulniu.billing.database.AppDataBase

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 4:40 PM
 * desc: 账单备注操作工具类
 */
object BillNoteSource {

    @JvmStatic
    fun addBillNote(billNote:BillNoteBean){
        AppDataBase.getInstance(App.getAppContext()).billNoteDao().insert(billNote)
    }

    @JvmStatic
    fun updateBillNote(billNote: BillNoteBean){
        AppDataBase.getInstance(App.getAppContext()).billNoteDao().update(billNote)
    }

    @JvmStatic
    fun getBillNotesByType(typeId:Int):List<BillNoteBean>?{
        return AppDataBase.getInstance(App.getAppContext()).billNoteDao().getBillNoteByType(typeId)
    }

}