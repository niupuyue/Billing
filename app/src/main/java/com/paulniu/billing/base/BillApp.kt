package com.paulniu.billing.base

import com.paulniu.bill_base_lib.base.App
import com.paulniu.bill_data_lib.bean.BaseType
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.bill_data_lib.source.BaseTypeSource
import com.paulniu.bill_data_lib.source.TypeSource
import com.paulniu.billing.BuildConfig
import com.paulniu.billing.R
import com.paulniu.billing.util.MetaDataUtil

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
        if (BaseTypeSource.queryBaseTypes().isEmpty()){
            BaseTypeSource.addOrUpdate(BaseType(0,"收入"))
            BaseTypeSource.addOrUpdate(BaseType(1,"支出"))
        }

        if(null == TypeSource.queryTypeInfosByBaseType(1) || TypeSource.queryTypeInfosByBaseType(1)?.isEmpty() == true || MetaDataUtil.shouldUpdateTypes){
            TypeSource.clearTypes()
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
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_sport, "运动", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_vegetable, "蔬菜", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_oil, "加油", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_amount, "礼金", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_clothes, "衣服", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_entertainment, "娱乐", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_firend, "亲友", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_house, "房子", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_household, "家居", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_kid, "孩子", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_travel, "旅行", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.app_icon_work, "办公", 1,BaseType(1,"支出")))
        }

        if (null == TypeSource.queryTypeInfosByBaseType(0) || TypeSource.queryTypeInfosByBaseType(0)?.isEmpty() == true){
            TypeSource.addOrUpdate(TypeInfo(0,R.mipmap.app_icon_financial,"理财",0,BaseType(0,"收入")))
        }
    }

}