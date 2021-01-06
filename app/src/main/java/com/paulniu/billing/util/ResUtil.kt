package com.paulniu.billing.util

import android.content.Context
import com.paulniu.billing.R
import java.lang.reflect.Field


/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2021/1/6 11:38 AM
 * desc: 利用反射 动态设置资源
 */
object ResUtil {

    fun mipmapResource(resName:String,context:Context): Int {
        return context.resources.getIdentifier(resName, "mipmap", context.packageName)
    }

    fun drawableResource(resName:String,context:Context): Int {
        return context.resources.getIdentifier(resName, "drawable", context.packageName)
    }

    fun colorResource(resName:String,context:Context): Int {
        return context.resources.getIdentifier(resName, "color", context.packageName)
    }

    fun stringResource(resName:String,context:Context): Int {
        return context.resources.getIdentifier(resName, "string", context.packageName)
    }

}