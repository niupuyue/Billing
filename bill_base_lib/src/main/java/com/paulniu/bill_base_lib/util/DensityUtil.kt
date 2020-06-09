package com.paulniu.bill_base_lib.util

import android.content.Context
import android.view.Display
import android.view.WindowManager
import com.paulniu.bill_base_lib.base.App

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/9 2:25 PM
 * desc: 尺寸相关工具类
 */
object DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(dpValue: Float): Int {
        val scale: Float = App.getAppContext().resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(context: Context?, pxValue: Float): Int {
        val scale: Float = App.getAppContext().resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context 上下文对象
     */
    fun getScreenWidth(context: Context): Int {
        val manager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display
        if (manager != null) {
            display = manager.defaultDisplay
            return display.width
        }
        return 0
    }

    /**
     * 获取屏幕的高度
     *
     * @param context 上下文对象
     */
    fun getScreenHeight(context: Context): Int {
        val manager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display
        if (manager != null) {
            display = manager.defaultDisplay
            return display.height
        }
        return 0
    }

}