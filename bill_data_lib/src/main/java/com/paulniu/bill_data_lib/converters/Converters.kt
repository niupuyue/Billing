package com.paulniu.bill_data_lib.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.paulniu.bill_data_lib.bean.BaseType
import com.paulniu.bill_data_lib.bean.TypeInfo
import java.util.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/8 6:32 PM
 * desc:
 */
object Converters {

    @TypeConverter
    @JvmStatic
    fun baseTypeToString(baseType: BaseType?): String? {
        return Gson().toJson(baseType)
    }

    @TypeConverter
    @JvmStatic
    fun stringToBaseType(json: String?): BaseType? {
        val type = object : TypeToken<BaseType>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun typeInfoToString(typeInfo: TypeInfo?): String? {
        return Gson().toJson(typeInfo)
    }

    @TypeConverter
    @JvmStatic
    fun stringToTypeInfo(json: String?): TypeInfo? {
        val type = object : TypeToken<TypeInfo>() {}.type
        return Gson().fromJson(json, type)
    }

}