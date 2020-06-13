package com.paulniu.billing.util

import android.content.pm.PackageManager
import android.os.Bundle
import com.paulniu.bill_base_lib.base.App

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/11 8:30 PM
 * desc: 获取 app 配置
 */
object MetaDataUtil {
    val metaData: Bundle = App.getAppContext().packageManager.getApplicationInfo(
        App.getAppContext().packageName, PackageManager.GET_META_DATA
    ).metaData

    // 获取是否需要重新添加types的类型(有时候重新编译会导致类型的资源id发生改变，最好每次更新时重新设置一次)
    val shouldUpdateTypes: Boolean = metaData.getBoolean("update_database_type",false)
}