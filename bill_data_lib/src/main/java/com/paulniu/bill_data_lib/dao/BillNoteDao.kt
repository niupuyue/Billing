package com.paulniu.bill_data_lib.dao

import androidx.room.*
import com.paulniu.bill_data_lib.bean.BillNoteBean

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 4:31 PM
 * desc:
 */
@Dao
abstract class BillNoteDao {

    @Update
    abstract fun update(billNote: BillNoteBean)

    @Insert
    abstract fun insert(billNote: BillNoteBean)

    @Delete
    abstract fun delete(billNote: BillNoteBean)

    /**
     * 根据类型id获取该类型下所有的备注
     */
    @Query("select * from BillNoteBean where typeId = :typeId order by count desc")
    abstract fun getBillNoteByType(typeId: Int): List<BillNoteBean>?

}