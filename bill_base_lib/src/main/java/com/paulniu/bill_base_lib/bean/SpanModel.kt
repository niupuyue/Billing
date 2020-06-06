package com.paulniu.bill_base_lib.bean

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/8 4:34 PM
 * desc: 下划线，点击事件等字符串处理
 */
data class SpanModel(
        /**
         * 文字
         */
        var text: String ? = null,

        /**
         * text 文字颜色
         */
        var color: Int = 0,

        /**
         * text 是否下划线
         */
        var isUnderline: Boolean = false,

        /**
         * text是否加粗
         */
        var isBold: Boolean = false,

        /**
         * 字体大小，大于 0 有效
         */
        var textSize: Float = 0f
)