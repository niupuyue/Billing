package com.paulniu.billing.base

import android.util.Log
import com.paulniu.bill_base_lib.base.App

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 7:11 PM
 * desc:
 */
class BillApp: App() {

    override fun onCreate() {
        super.onCreate()
        Log.e("NPL","执行了BillApp-onCreate方法")
    }

}