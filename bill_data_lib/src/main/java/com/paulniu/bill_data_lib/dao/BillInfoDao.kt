package com.paulniu.bill_data_lib.dao

import androidx.room.*
import com.paulniu.bill_data_lib.bean.BillInfo

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/8 7:57 PM
 * desc:
 */
@Dao
abstract class BillInfoDao {

    @Update
    abstract fun update(billInfo: BillInfo)

    @Insert
    abstract fun insert(billInfo: BillInfo)

    @Delete
    abstract fun delete(billInfo: BillInfo)

    /**
     * 添加或更新bill
     */
    fun addOrUpdate(billInfo: BillInfo) {
        val oldBillInfo = getBillInfoById(billInfo.id)
        if (null == oldBillInfo) {
            insert(billInfo)
        } else {
            billInfo.id = oldBillInfo.id
            update(billInfo)
        }
    }

    /**
     * 获取所有的bill信息
     */
    @Query("select * from BillInfo")
    abstract fun getBillInfoList(): List<BillInfo>

    /**
     * 根据id查询对应的bill信息
     */
    @Query("select * from BillInfo where id = :billId")
    abstract fun getBillInfoById(billId: Int): BillInfo

    /**
     * 获取某个时间段内容所有的账单
     */
    @Query("select * from BillInfo where time between :startTime and :endTime order by time desc")
    abstract fun getBillsByTime(startTime: Long, endTime: Long): List<BillInfo>

    /**
     * 计算某一个时间段内的余额
     */
    @Query("select sum(money) from BillInfo where time between :startTime and :endTime")
    abstract fun getMoneyByTimes(startTime: Long, endTime: Long): Float

    /**
     * 计算某段时间内某一个类型的金额
     */
    @Query("select sum(money) from billinfo where typeId == :type and time between :startTime and :endTime")
    abstract fun getMoneyByTimesType(startTime: Long, endTime: Long, type: Int):Float?

}