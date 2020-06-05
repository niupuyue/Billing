package com.paulniu.billing.database

import com.github.yuweiguocn.library.greendao.MigrationHelper
import com.paulniu.bill_base_lib.constant.Constant
import com.paulniu.bill_base_lib.base.App
import com.paulniu.bill_data_lib.BuildConfig
import com.paulniu.bill_data_lib.MySqliteHelper
import com.paulniu.bill_data_lib.dao.DaoMaster
import com.paulniu.bill_data_lib.dao.DaoSession

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 5:30 PM
 * desc: 数据库操作基类,所有获取数据库操作对象的Session全部从此处获取
 */
object AppDataBase {

    @JvmStatic
    lateinit var daoSession: DaoSession

    init {
        // 判断是否是Debug模式
        MigrationHelper.DEBUG = BuildConfig.DEBUG
        // 创建数据库
        val mySqliteHelper = MySqliteHelper(App.INSTANCE!!, Constant.SQLITE_NAME)
        // 声明一个Sqlite
        val db = mySqliteHelper.writableDb
        val daoMaster = DaoMaster(db)
        daoSession = daoMaster.newSession()
    }

}