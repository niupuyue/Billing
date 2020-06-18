package com.paulniu.bill_data_lib.bean

import androidx.room.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 3:56 PM
 * desc: 账单备注对象
 */
@Entity(indices = [Index(value = ["id", "title", "typeId"])])
data class BillNoteBean(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "typeId")
    var typeId: Int? = null,
    @ColumnInfo(name = "type")
    var type: TypeInfo? = null,
    @ColumnInfo(name = "time")
    var time: Long? = System.currentTimeMillis(), // 倒序取出数据
    @ColumnInfo(name = "count")
    var count: Int = 0,
    @Ignore
    var isSelected: Boolean = false
)