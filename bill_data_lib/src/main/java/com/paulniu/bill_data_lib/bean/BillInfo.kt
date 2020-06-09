package com.paulniu.bill_data_lib.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 8:06 PM
 * desc: 账单实例
 */
@Entity(indices = [Index(value = ["id", "title", "typeId"])])
data class BillInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "money")
    var money: Float = 0f,

    @ColumnInfo(name = "typeId")
    var typeId: Int? = null,

    @ColumnInfo(name = "type")
    var typeInfo: TypeInfo? = null,

    @ColumnInfo(name = "time")
    var time: Long = 0
)