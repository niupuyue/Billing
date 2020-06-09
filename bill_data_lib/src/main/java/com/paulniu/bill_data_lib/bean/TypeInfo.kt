package com.paulniu.bill_data_lib.bean

import androidx.room.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 8:06 PM
 * desc: 账单类型实例
 */

@Entity(indices = [Index(value = ["id", "title"])])
data class TypeInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "iconRes")
    var iconRes: Int? = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "baseTypeId")
    var baseTypeId: Int? = null,

    @ColumnInfo(name = "baseType")
    var baseType: BaseType? = null,

    @Ignore
    var isSelected: Boolean = false
)