package com.paulniu.billing.base

import android.util.Log
import com.paulniu.bill_base_lib.base.App
import com.paulniu.bill_data_lib.bean.TypeBean
import com.paulniu.bill_data_lib.source.TypeSource
import com.paulniu.billing.R

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 7:11 PM
 * desc:
 */
class BillApp : App() {

    override fun onCreate() {
        super.onCreate()
        initDatabase()

    }

    /**
     * 初始化类型数据库
     */
    private fun initDatabase() {
        // 查询数据库中type是否为null，并且是第一次安装
        if (TypeSource.queryBillType().isEmpty()) {
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_food, "餐饮", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_book, "书籍", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_edition, "教育", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_shopping, "购物", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_fruite, "水果", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_snack, "零食", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_communication, "通信", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_traffic, "交通", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_social, "社交", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_pet, "宠物", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_express, "快递", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_cion_sport, "运动", 0))
            TypeSource.addOrUpdateBillType(TypeBean(null, R.mipmap.app_icon_vegetable, "蔬菜", 0))
        }
    }

}