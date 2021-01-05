package com.paulniu.bill_base_lib.util

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.paulniu.bill_base_lib.bean.SpanClickable
import com.paulniu.bill_base_lib.bean.SpanModel
import com.paulniu.bill_base_lib.listener.ITextViewClickListener
import java.util.ArrayList

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/6 2:44 PM
 * desc: 字符串设置不同字体颜色，设置点击事件
 */
object StringControlUtil {
    /**
     * 可同时设置字体颜色、是否下划线、是否加粗、文字大小和点击事件
     *
     * @param tv           TextView
     * @param text         要显示的全部文本
     * @param hotWordModel @see SpanModel
     * @param listener     点击监听
     */
    fun setSpanText(tv: TextView?, text: CharSequence, hotWordModel: List<SpanModel>?, listener: ITextViewClickListener?) {
        if (tv == null || TextUtils.isEmpty(text) || null == hotWordModel) {
            return
        }
        try {
            val textBuilder = SpannableStringBuilder(text)
            var lis: List<Int>
            for (i in hotWordModel.indices) {
                lis = getStrIndex(text.toString() + "", hotWordModel[i].text?:"")
                for (j in lis.indices) {
                    textBuilder.setSpan(
                        SpanClickable(listener, i, hotWordModel[i].isUnderline, hotWordModel[i].color, hotWordModel[i].isBold, hotWordModel[i].textSize),
                        lis[j],
                        lis[j] + (hotWordModel[i].text?.length?:0),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
            tv.movementMethod = LinkMovementMethod.getInstance()
            tv.highlightColor = Color.TRANSPARENT
            tv.text = textBuilder
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * @param tv
     * @param text            Text
     * @param arrHotWords     关键词
     * @param colorResourceID 关键词颜色
     * @param isBold          关键词是否加粗
     * @param textSize        关键字文字大小
     * @param listener        关键字点击回调
     */
    fun setHotWordsText(context: Context?, tv: TextView?, text: CharSequence, arrHotWords: Array<String>?, colorResourceID: Int, isBold: Boolean, isUnderline: Boolean, textSize: Float, listener: ITextViewClickListener?) {
        if (tv == null || TextUtils.isEmpty(text) || null == arrHotWords || context == null) {
            return
        }
        try {
            val hotWordModels: MutableList<SpanModel> = ArrayList()
            var spanModel: SpanModel
            for (arrHotWord in arrHotWords) {
                spanModel = SpanModel()
                spanModel.text = arrHotWord
                spanModel.isUnderline = isUnderline
                spanModel.color = ContextCompat.getColor(context, colorResourceID)
                spanModel.isBold = isBold
                spanModel.textSize = textSize
                hotWordModels.add(spanModel)
            }
            setSpanText(tv, text, hotWordModels, listener)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * @param tv
     * @param text            Text
     * @param arrHotWords     关键词
     * @param colorResourceID 关键词颜色
     * @param isBold          关键词是否加粗
     * @param listener        关键字点击回调
     */
    fun setHotWordsText(context: Context?, tv: TextView?, text: CharSequence, arrHotWords: Array<String>?, colorResourceID: Int, isBold: Boolean, isUnderline: Boolean, listener: ITextViewClickListener?) {
        setHotWordsText(context, tv, text, arrHotWords, colorResourceID, isBold, isUnderline, -1f, listener)
    }

    /**
     * @param tv
     * @param text            Text
     * @param arrHotWords     关键词
     * @param colorResourceID 关键词颜色
     * @param listener        关键字点击回调
     */
    fun setHotWordsText(context: Context?, tv: TextView?, text: CharSequence, arrHotWords: Array<String>?, colorResourceID: Int, isUnderline: Boolean, listener: ITextViewClickListener?) {
        setHotWordsText(context, tv, text, arrHotWords, colorResourceID, false, isUnderline, listener)
    }

    /**
     * @param tv
     * @param text            Text
     * @param hotWords        关键词
     * @param colorResourceID 关键词颜色
     * @param listener        关键字点击回调
     */
    fun setHotWordsText(context: Context?, tv: TextView?, text: CharSequence, hotWords: String, colorResourceID: Int, listener: ITextViewClickListener?) {
        setHotWordsText(context, tv, text, arrayOf(hotWords), colorResourceID, false, listener)
    }

    fun setHotWordsText(context: Context?, tv: TextView?, text: CharSequence, arrHotWords: Array<String>?, colorResourceId: Int, listener: ITextViewClickListener?) {
        setHotWordsText(context, tv, text, arrHotWords, colorResourceId, false, false, listener)
    }

    /**
     * @param tv
     * @param text            Text
     * @param hotWords        关键词
     * @param colorResourceID 关键词颜色
     */
    fun setHotWordsText(context: Context?, tv: TextView?, text: CharSequence, hotWords: String, colorResourceID: Int) {
        setHotWordsText(context, tv, text, hotWords, colorResourceID, null)
    }

    /**
     * @param tv
     * @param text            Text
     * @param arrHotWords     关键词
     * @param colorResourceID 关键词颜色
     */
    fun setHotWordsText(context: Context?, tv: TextView?, text: CharSequence, arrHotWords: Array<String>?, colorResourceID: Int) {
        setHotWordsText(context, tv, text, arrHotWords, colorResourceID, false, null)
    }

    /**
     * 获取关键字在字符串的indexOf集合 (忽略大小写)
     *
     * @param str     字符串
     * @param hotword 关键字
     * @return
     */
    fun getStrIndex(str: String, hotword: String): List<Int> {
        val lis: MutableList<Int> = ArrayList()

        // 忽略大小写
        val tempLowerCaseStr = str.toLowerCase()
        val tempLowerHotWord = hotword.toLowerCase()
        var indexOf = tempLowerCaseStr.indexOf(tempLowerHotWord)
        if (indexOf != -1) {
            lis.add(indexOf)
        }
        while (indexOf != -1) {
            indexOf = tempLowerCaseStr.indexOf(tempLowerHotWord, indexOf + 1)
            if (indexOf != -1) {
                lis.add(indexOf)
            }
        }
        return lis
    }
}