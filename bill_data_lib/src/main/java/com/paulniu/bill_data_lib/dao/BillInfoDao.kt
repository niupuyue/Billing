package com.paulniu.bill_data_lib.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

}