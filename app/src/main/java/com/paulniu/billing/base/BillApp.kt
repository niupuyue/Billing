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
            TypeSource.addOrUpdate(TypeInfo(0, 1, "餐饮", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 2, "书籍", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 3, "教育", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 4, "购物", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 5, "水果", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 6, "零食", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0,7, "通信", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 8, "交通", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 9, "社交", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 10, "宠物", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 11, "快递", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 12, "运动", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 13, "蔬菜", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 14, "加油", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 15, "礼金", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 16, "衣服", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 17, "娱乐", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 18, "亲友", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 19, "房子", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 20, "家居", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 21, "孩子", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 22, "旅行", 1,BaseType(1,"支出")))
            TypeSource.addOrUpdate(TypeInfo(0, 23, "办公", 1,BaseType(1,"支出")))
        }

        if (null == TypeSource.queryTypeInfosByBaseType(0) || TypeSource.queryTypeInfosByBaseType(0)?.isEmpty() == true){
            TypeSource.addOrUpdate(TypeInfo(0,R.mipmap.app_icon_financial,"理财",0,BaseType(0,"收入")))
        }
    }

}