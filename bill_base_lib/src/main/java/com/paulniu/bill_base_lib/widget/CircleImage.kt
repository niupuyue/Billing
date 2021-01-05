package com.paulniu.bill_base_lib.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/10 8:00 PM
 * desc: 圆形图片
 */
class CircleImage constructor(context: Context, attr: AttributeSet) :
    androidx.appcompat.widget.AppCompatImageView(context, attr) {

    private lateinit var mPaint: Paint

    private var mRadius: Int? = null

    private var mScale: Float? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 圆形图片，保证宽高是一样的
        val size = measuredWidth.coerceAtMost(measuredHeight)
        mRadius = size / 2
        setMeasuredDimension(size, size)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        mPaint = Paint()
        val bitmap = drawableToBitmap(drawable)

        // 初始化BitmapShader，传入bitmap对象
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        // 计算缩放比例
        mScale = ((mRadius ?: 0) * 2f) / bitmap.height.coerceAtMost(bitmap.width)

        val matrix = Matrix()
        matrix.setScale(mScale ?: 0f, mScale ?: 0f)
        bitmapShader.setLocalMatrix(matrix)

        mPaint.shader = bitmapShader
        canvas?.drawCircle(
            mRadius?.toFloat() ?: 0f,
            mRadius?.toFloat() ?: 0f,
            mRadius?.toFloat() ?: 0f,
            mPaint
        )
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        return bitmap
    }

}