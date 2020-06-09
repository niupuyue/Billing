package com.paulniu.billing.database

import com.paulniu.bill_base_lib.base.App
import com.paulniu.bill_base_lib.util.TimeUtil
import com.paulniu.bill_data_lib.bean.BillInfo


/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 6:39 PM
 * desc: 账单数据库操作实现类
 */
object BillSource {

    /**
     * 添加一个账单
     */
    @JvmStatic
    fun addOrUpdate(billInfo: BillInfo) {
        AppDataBase.getInstance(App.getAppContext()).billInfoDao().addOrUpdate(billInfo)
    }

    /**
     * 删除一个账单
     */
    @JvmStatic
    fun deleteBillById(id: Int) {
        val deleteBillInfo = getBillInfoById(id)
        if (null != deleteBillInfo)
            AppDataBase.getInstance(App.getAppContext()).billInfoDao().delete(deleteBillInfo)
    }

    /**
     * 根据id获取账单信息
     */
    @JvmStatic
    fun getBillInfoById(id: Int): BillInfo? {
        return AppDataBase.getInstance(App.getAppContext()).billInfoDao().getBillInfoById(id)
    }

    /**
     * 查询所有账单
     */
    @JvmStatic
    fun queryBillsAll(): List<BillInfo> {
        return AppDataBase.getInstance(App.getAppContext()).billInfoDao().getBillInfoList()
    }

    /**
     * 查询当天的所有账单
     */
    @JvmStatic
    fun queryBillByDay(time: Long = System.currentTimeMillis()): List<BillInfo> {
        return AppDataBase.getInstance(App.getAppContext()).billInfoDao()
            .getBillsByTime(
                TimeUtil.getDayStartAndEnd(time)[0],
                TimeUtil.getDayStartAndEnd(time)[1]
            )
    }

    /**
     * 查询当月的所有账单
     */
    @JvmStatic
    fun queryBillByMonth(time: Long = System.currentTimeMillis()): List<BillInfo> {
        return AppDataBase.getInstance(App.getAppContext()).billInfoDao().getBillsByTime(
            TimeUtil.getMonthStartAndEnd(time)[0],
            TimeUtil.getMonthStartAndEnd(time)[1]
        )
    }

}