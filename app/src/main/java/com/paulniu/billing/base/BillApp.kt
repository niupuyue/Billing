package com.paulniu.billing.base

import com.paulniu.bill_base_lib.base.App
import com.paulniu.bill_data_lib.bean.BaseType
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.bill_data_lib.source.BaseTypeSource
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
        if (null == BaseTypeSource.queryBaseTypes() || BaseTypeSource.queryBaseTypes()!!.isEmpty()){
            BaseTypeSource.addOrUpdate(BaseType(0,"收入"))
            BaseTypeSource.addOrUpdate(BaseType(1,"支出"))
        }

        if(null == TypeSource.queryTypeInfosByBaseType(1) || TypeSource.queryTypeInfosByBaseType(1)!!.isEmpty()){
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_food, "餐饮", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_book, "书籍", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_edition, "教育", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_shopping, "购物", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_fruite, "水果", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_snack, "零食", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_communication, "通信", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_traffic, "交通", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_social, "社交", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_pet, "宠物", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_express, "快递", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_cion_sport, "运动", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_vegetable, "蔬菜", 1,BaseType(1,"支出")))
        }

        if (null == TypeSource.queryTypeInfosByBaseType(0) || TypeSource.queryTypeInfosByBaseType(0)!!.isEmpty()){
            TypeSource.addOrUpdate(TypeInfo(0,R.mipmap.app_icon_financial,"理财",0,BaseType(0,"收入")))
        }
    }

}