package com.paulniu.bill_data_lib.dao

import androidx.room.*
import com.paulniu.bill_data_lib.bean.TypeInfo

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/8 8:00 PM
 * desc:
 */
@Dao
abstract class TypeInfoDao {

    @Update
    abstract fun update(typeInfo: TypeInfo)

    @Insert
    abstract fun insert(typeInfo: TypeInfo)

    @Delete
    abstract fun delete(typeInfo: TypeInfo)

    fun addOrUpdate(typeInfo: TypeInfo) {
        val oldTypeInfo = queryTypeInfoById(typeInfo.id!!)
        if (null == oldTypeInfo) {
            insert(typeInfo)
        } else {
            typeInfo.id = oldTypeInfo.id
            update(typeInfo)
        }
    }

    /**
     * 查询所有的types
     */
    @Query("select * from typeinfo")
    abstract fun queryTypeInfos():List<TypeInfo>

    /**
     * 根据id获取type对象
     */
    @Query("select * from typeinfo where id = :typeId")
    abstract fun queryTypeInfoById(typeId: Int?): TypeInfo?

    /**
     * 根据baseType获取type对象集合
     */
    @Query("select * from typeinfo where baseTypeId = :baseTypeId")
    abstract fun queryTypeInfosByBaseType(baseTypeId: Int): List<TypeInfo>

}