package com.paulniu.bill_data_lib.source

import android.text.TextUtils
import com.paulniu.bill_base_lib.base.App
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.billing.database.AppDataBase


/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 8:32 PM
 * desc: 类型操作工具类
 */
object TypeSource {

    /**
     * 清除类型数据
     */
    @JvmStatic
    fun clearTypes() {
        val types = queryTypes()
        if (types.isNotEmpty()) {
            types.forEach { value ->
                AppDataBase.getInstance(App.getAppContext()).typeInfoDao().delete(value)
            }
        }
    }

    /**
     * 插入或者更新类型
     */
    @JvmStatic
    fun addOrUpdate(typeInfo: TypeInfo) {
        if (typeInfo.id < 0 || TextUtils.isEmpty(typeInfo.title) || typeInfo.iconRes?:0 <= 0) {
            return
        }
        AppDataBase.getInstance(App.getAppContext()).typeInfoDao().addOrUpdate(typeInfo)
    }

    /**
     * 执行插入操作
     */
    @JvmStatic
    fun addTypeInfo(typeInfo: TypeInfo) {
        if (typeInfo.id < 0 || TextUtils.isEmpty(typeInfo.title) || typeInfo.iconRes?:0 <= 0) {
            return
        }
        AppDataBase.getInstance(App.getAppContext()).typeInfoDao().insert(typeInfo)
    }

    /**
     * 获取所有的类型
     */
    @JvmStatic
    fun queryTypes(): List<TypeInfo> {
        return AppDataBase.getInstance(App.getAppContext()).typeInfoDao().queryTypeInfos()
    }

    /**
     * 根据baseType获取typeInfo对象
     */
    @JvmStatic
    fun queryTypeInfosByBaseType(baseTypeId: Int): List<TypeInfo>? {
        if (baseTypeId < 0) {
            return null
        }
        return AppDataBase.getInstance(App.getAppContext()).typeInfoDao()
            .queryTypeInfosByBaseType(baseTypeId)
    }

    /**
     * 根据typeInfo的id获取typeInfo对象
     */
    @JvmStatic
    fun queryTypeInfoById(id: Int): TypeInfo? {
        if (id < 0) {
            return null
        }
        return AppDataBase.getInstance(App.getAppContext()).typeInfoDao().queryTypeInfoById(id)
    }

}