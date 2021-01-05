package com.paulniu.bill_base_lib.util

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/6 2:26 PM
 * desc: 字符串操作相关类
 */

object StringUtil {

    /**
     * 清除字符串中的空格
     */
    fun formatStringWithoutSpace(value: String?): String {
        if (value?.isEmpty() == true) return ""
        val p: Pattern = Pattern.compile("\\s*|\t|\r|\n")
        val m: Matcher = p.matcher(value)
        return m.replaceAll("")
    }
}