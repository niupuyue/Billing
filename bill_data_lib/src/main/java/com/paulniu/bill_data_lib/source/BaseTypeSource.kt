package com.paulniu.bill_data_lib.source

import android.text.TextUtils
import com.paulniu.bill_base_lib.base.App
import com.paulniu.bill_data_lib.bean.BaseType
import com.paulniu.billing.database.AppDataBase

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/8 8:07 PM
 * desc:
 */
object BaseTypeSource {

    /**
     * 插入或者更新BaseType
     */
    @JvmStatic
    fun addOrUpdate(baseType: BaseType) {
        if (baseType.id ?: 0 < 0 || TextUtils.isEmpty(baseType.title)) {
            return
        }
        AppDataBase.getInstance(App.getAppContext()).baseTypeDao().addOrUpdate(baseType)
    }

    /**
     * 查询所有的baseType
     */
    @JvmStatic
    fun queryBaseTypes(): List<BaseType> {
        return AppDataBase.getInstance(App.getAppContext()).baseTypeDao().getBaseTypes()
    }

}