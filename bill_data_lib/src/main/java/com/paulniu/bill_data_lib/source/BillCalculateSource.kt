package com.paulniu.bill_data_lib.source

import androidx.room.Query
import com.paulniu.bill_base_lib.base.App
import com.paulniu.billing.database.AppDataBase

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/9 1:17 PM
 * desc: 账单相关计算操作
 */
object BillCalculateSource {

    /**
     * 计算某一段时间内的花销
     */
    @JvmStatic
    fun sumMoneyByTimes(startTime:Long,endTime:Long):Float{
        return AppDataBase.getInstance(App.getAppContext()).billInfoDao().getMoneyByTimes(startTime,endTime)
    }

    /**
     * 计算某段时间内某一个类型的花销
     */
    @JvmStatic
    fun sumMoneyByTimesType(startTime: Long,endTime: Long,type:Int):Float?{
        return AppDataBase.getInstance(App.getAppContext()).billInfoDao().getMoneyByTimesType(startTime,endTime,type)
    }

}