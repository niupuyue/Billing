package com.paulniu.bill_base_lib.base

import android.app.Application

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 5:43 PM
 * desc: 全局Application
 */
open class App : Application() {

    companion object {
        var INSTANCE:App ?= null
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}