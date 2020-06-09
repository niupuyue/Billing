package com.paulniu.billing.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.paulniu.bill_base_lib.constant.Constant
import com.paulniu.bill_data_lib.bean.BaseType
import com.paulniu.bill_data_lib.bean.BillInfo
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.bill_data_lib.converters.Converters
import com.paulniu.bill_data_lib.dao.BaseTypeDao
import com.paulniu.bill_data_lib.dao.BillInfoDao
import com.paulniu.bill_data_lib.dao.TypeInfoDao


/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 5:30 PM
 * desc: 数据库操作基类,所有获取数据库操作对象的Session全部从此处获取
 */
@Database(entities = [BaseType::class, TypeInfo::class, BillInfo::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDataBase? = null

        // 数据库升级
        val mirgration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }

        }

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
            if (null == INSTANCE) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    Constant.SQLITE_NAME
                )
                    .setJournalMode(JournalMode.TRUNCATE)
                    .allowMainThreadQueries()
                    .addMigrations(mirgration_1_2)
                    .build()
            }
            return INSTANCE!!
        }
    }

    abstract fun billInfoDao(): BillInfoDao

    abstract fun typeInfoDao(): TypeInfoDao

    abstract fun baseTypeDao(): BaseTypeDao

}