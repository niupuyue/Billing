package com.paulniu.bill_data_lib

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.paulniu.bill_data_lib.dao.DaoMaster
import org.greenrobot.greendao.database.Database

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 6:29 PM
 * desc: 数据库系统级操作类，包括数据库的创建，数据库的升级
 */
class MySqliteHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory? = null
) :
    DaoMaster.OpenHelper(context, name, factory) {

    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
        // 根据版本内容升级数据库
    }

}