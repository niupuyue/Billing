package com.paulniu.bill_base_lib.base

import android.app.Application
import android.content.Context

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 5:43 PM
 * desc: 全局Application
 */
open class App : Application() {

    companion object {
        @JvmStatic
        private var context: Context? = null

        @JvmStatic
        fun getAppContext(): Context {
            return context!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}