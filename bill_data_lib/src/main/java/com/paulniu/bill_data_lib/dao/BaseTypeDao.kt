package com.paulniu.bill_data_lib.dao

import android.text.TextUtils
import androidx.room.*
import com.paulniu.bill_data_lib.bean.BaseType

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/8 8:05 PM
 * desc:
 */
@Dao
abstract class BaseTypeDao {

    @Insert
    abstract fun insert(baseType: BaseType)

    @Update
    abstract fun update(baseType: BaseType)

    @Delete
    abstract fun delete(baseType: BaseType)

    @Query("select * from BaseType")
    abstract fun getBaseTypes(): List<BaseType>

    @Query("select * from basetype where id =:id")
    abstract fun getBaseTypeById(id: Int?): BaseType?

    fun addOrUpdate(baseType: BaseType) {
        if (baseType.id?:0 < 0 || TextUtils.isEmpty(baseType.title)) {
            return
        }
        val oldBaseType = getBaseTypeById(baseType.id)
        if (null == oldBaseType) {
            insert(baseType)
        } else {
            baseType.id = oldBaseType.id
            update(baseType)
        }
    }

}