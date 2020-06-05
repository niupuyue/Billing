package com.paulniu.bill_data_lib.source

import com.paulniu.bill_data_lib.bean.TypeBean
import com.paulniu.billing.database.AppDataBase

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 8:32 PM
 * desc: 类型操作工具类
 */
object TypeSource {

    /**
     * 插入或更新账单类型
     */
    fun addOrUpdateBillType(type:TypeBean){
        AppDataBase.daoSession.insertOrReplace(type)
    }



}