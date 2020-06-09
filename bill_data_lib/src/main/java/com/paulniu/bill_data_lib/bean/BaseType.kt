package com.paulniu.bill_data_lib.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/8 6:15 PM
 * desc:
 */
@Entity(indices = [Index(value = ["id", "title"])])
data class BaseType(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? = null
)
