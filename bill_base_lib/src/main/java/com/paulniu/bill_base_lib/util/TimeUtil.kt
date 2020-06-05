package com.paulniu.bill_base_lib.util

import android.text.format.DateFormat
import com.paulniu.bill_base_lib.constant.TimeConstant
import java.util.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 8:40 PM
 * desc: 时间操作工具类
 */
object TimeUtil {

    /**
     * 根据一个时间time，获取当前的开始和结束
     */
    fun getDayStartAndEnd(time: Long = System.currentTimeMillis()): LongArray {
        val resultArray = LongArray(2)
        val date = Date(time)
        val calendarStart = GregorianCalendar()
        calendarStart.set(date.year, date.month, date.day)
        calendarStart.set(Calendar.HOUR_OF_DAY, 0)
        calendarStart.set(Calendar.MINUTE, 0)
        calendarStart.set(Calendar.SECOND, 0)
        resultArray[0] = calendarStart.timeInMillis
        val calendarEnd = GregorianCalendar()
        calendarEnd.set(date.year, date.month, date.day)
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23)
        calendarEnd.set(Calendar.MINUTE, 59)
        calendarEnd.set(Calendar.SECOND, 59)
        resultArray[1] = calendarEnd.timeInMillis
        return resultArray
    }

    /**
     * 根据一个时间time，获取当前月的开始和结束
     */
    fun getMonthStartAndEnd(time: Long = System.currentTimeMillis()): LongArray {
        val resultArray = LongArray(2)
        val date = Date(time)
        val calendarStart = GregorianCalendar()
        calendarStart.set(date.year, date.month)
        calendarStart.set(Calendar.DAY_OF_MONTH, 1)
        calendarStart.set(Calendar.HOUR_OF_DAY, 0)
        calendarStart.set(Calendar.MINUTE, 0)
        calendarStart.set(Calendar.SECOND, 0)
        resultArray[0] = calendarStart.timeInMillis
        val calendarEnd = GregorianCalendar()
        calendarEnd.set(date.year, date.month)
        calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMaximum(Calendar.DAY_OF_MONTH))
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23)
        calendarEnd.set(Calendar.MINUTE, 59)
        calendarEnd.set(Calendar.SECOND, 59)
        resultArray[1] = calendarEnd.timeInMillis
        return resultArray
    }

    /**
     * 将时间转换成固定的格式
     */
    fun formatTimtToString(
        time: Long = System.currentTimeMillis(),
        type: String = TimeConstant.TYPE_YEAR_MONTH_DAY
    ): String {
        return DateFormat.format(type, time).toString()
    }

}