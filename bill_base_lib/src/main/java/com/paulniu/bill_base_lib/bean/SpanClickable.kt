package com.paulniu.bill_base_lib.bean

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.NonNull
import com.paulniu.bill_base_lib.listener.ITextViewClickListener

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/6 2:46 PM
 * desc:
 */

class SpanClickable(
    private val clickListener: ITextViewClickListener?,
    private val position: Int,
    private val isUnderline: Boolean,
    private val color: Int,
    isBold: Boolean,
    textSize: Float
) : ClickableSpan(), View.OnClickListener {
    private var isBold = false
    private var textSize: Float
    override fun onClick(v: View) {
        clickListener?.onSpanClick(position)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = isUnderline
        ds.color = color
        if (isBold) {
            ds.typeface = Typeface.DEFAULT_BOLD
        }
        if (textSize > 0.0f) {
            ds.textSize = textSize
        }
    }

    init {
        this.isBold = isBold
        this.textSize = textSize
    }
}