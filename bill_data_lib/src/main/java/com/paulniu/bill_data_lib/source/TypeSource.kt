package com.paulniu.bill_data_lib.source

import com.paulniu.bill_data_lib.bean.TypeBean
import com.paulniu.bill_data_lib.dao.TypeBeanDao
import com.paulniu.billing.database.AppDataBase

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 8:32 PM
 * desc: 类型操作工具类
 */
object TypeSource {

    /**
     * 插入或更新账单类型
     */
    fun addOrUpdateBillType(type: TypeBean) {
        AppDataBase.daoSession.clear()
        AppDataBase.daoSession.insertOrReplace(type)
    }

    /**
     * 获取所有的账单类型
     */
    fun queryBillType(): List<TypeBean> {
        AppDataBase.daoSession.clear()
        return AppDataBase.daoSession.queryBuilder(TypeBean::class.java).build().list()
    }

    /**
     * 根据id获取对应的账单类型
     */
    fun queryBillById(id: Long): TypeBean {
        AppDataBase.daoSession.clear()
        return AppDataBase.daoSession.queryBuilder(TypeBean::class.java)
            .where(TypeBeanDao.Properties.Id.eq(id)).build().list().first()
    }

}