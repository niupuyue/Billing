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
     * 获取当前的年份
     */
    fun getYear(time: Long = System.currentTimeMillis()): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date(time)
        return calendar.get(Calendar.YEAR)
    }

    /**
     * 获取当前的月份
     */
    fun getMonth(time: Long = System.currentTimeMillis()): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date(time)
        return calendar.get(Calendar.MONTH)
    }

    /**
     * 获取当前的天
     */
    fun getDay(time: Long = System.currentTimeMillis()): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date(time)
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    /**
     * 计算某一天的当前时间
     */
    fun getCurrentTimeByDay(year: Int, month: Int, day: Int): Long {
        val calendar = Calendar.getInstance()
        val tempOffset = System.currentTimeMillis() - getDayStartAndEnd()[0]
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.timeInMillis + tempOffset
    }

    /**
     * 根据传递过来的time，返回对应的年，月，日数组
     */
    fun getYearMonthDayByTime(time: Long = System.currentTimeMillis()): IntArray {
        var result = IntArray(3)
        val calendar = Calendar.getInstance()
        calendar.time = Date(time)
        result[0] = calendar.get(Calendar.YEAR)
        result[1] = calendar.get(Calendar.MONTH)
        result[2] = calendar.get(Calendar.DAY_OF_MONTH)
        return result
    }

    /**
     * 根据一个时间time，获取当前的开始和结束
     */
    fun getDayStartAndEnd(time: Long = System.currentTimeMillis()): LongArray {
        val resultArray = LongArray(2)
        val date = Date(time)
        val calendarStart = Calendar.getInstance()
        calendarStart.time = date
        calendarStart.set(Calendar.HOUR_OF_DAY, 0)
        calendarStart.set(Calendar.MINUTE, 0)
        calendarStart.set(Calendar.SECOND, 0)
        resultArray[0] = calendarStart.timeInMillis
        val calendarEnd = Calendar.getInstance()
        calendarEnd.time = date
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
        val calendarStart = Calendar.getInstance()
        calendarStart.time = date
        calendarStart.set(Calendar.DAY_OF_MONTH, 1)
        calendarStart.set(Calendar.HOUR_OF_DAY, 0)
        calendarStart.set(Calendar.MINUTE, 0)
        calendarStart.set(Calendar.SECOND, 0)
        resultArray[0] = calendarStart.timeInMillis
        val calendarEnd = Calendar.getInstance()
        calendarEnd.time = date
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
    fun formatTimeToString(
        time: Long = System.currentTimeMillis(),
        type: String = TimeConstant.TYPE_YEAR_MONTH_DAY
    ): String {
        return DateFormat.format(type, time).toString()
    }

    /**
     * 将时间转换成如下的格式 6月7号  06/07
     */
    fun formatTimeToMonthAndDay(time: Long = System.currentTimeMillis()): String {
        return DateFormat.format(TimeConstant.TYPE_MONTH_DAY, time).toString()
    }

    /**
     * 根据时间戳计算日期是星期几
     */
    fun formatTimeToWeek(time: Long = System.currentTimeMillis()): String {
        val calendar = Calendar.getInstance()
        val date = Date(time)
        calendar.time = date
        return when (calendar.firstDayOfWeek) {
            0 -> {
                "周日"
            }
            1 -> {
                "周一"
            }
            2 -> {
                "周二"
            }
            3 -> {
                "周三"
            }
            4 -> {
                "周四"
            }
            5 -> {
                "周五"
            }
            6 -> {
                "周六"
            }
            else -> {
                "未知"
            }
        }
    }

}