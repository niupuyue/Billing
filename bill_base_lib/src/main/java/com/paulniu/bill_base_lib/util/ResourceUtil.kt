package com.paulniu.bill_base_lib.util

import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.paulniu.bill_base_lib.base.App

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2021/1/5 12:10 PM
 * desc: 资源相关的工具类
 */
object ResourceUtil {

    @JvmStatic
    fun getDrawable(@DrawableRes resId: Int): Drawable {
        return App.getAppContext().resources.getDrawable(resId, null)
    }

    @JvmStatic
    fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(App.getAppContext(),id)
    }

    @JvmStatic
    fun getBoolean(@BoolRes id: Int): Boolean {
        return App.getAppContext().resources.getBoolean(id)
    }

    @JvmStatic
    fun getInteger(@IntegerRes id: Int): Int {
        return App.getAppContext().resources.getInteger(id)
    }

    @JvmStatic
    fun getDimension(@DimenRes id: Int): Float {
        return App.getAppContext().resources.getDimension(id)
    }

}