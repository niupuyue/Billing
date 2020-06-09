package com.paulniu.billing.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.paulniu.bill_base_lib.util.DensityUtil
import com.paulniu.billing.R
import com.paulniu.billing.listener.IMainBillListDeleteListener
import kotlinx.android.synthetic.main.view_dialog_delete_bill.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/9 1:56 PM
 * desc: 账单删除弹窗
 */
class MainBillListDeleteDialog(context: Context, listener: IMainBillListDeleteListener) :
    Dialog(context) {

    private var mListener: IMainBillListDeleteListener? = null

    init {
        mListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_dialog_delete_bill)

//        val params = window?.attributes
//        params?.width = DensityUtil.getScreenWidth(context) * 0.8.toInt()
//        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
//        window?.attributes = params

        // 设置弹窗样式
        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        delete_bill_dialog_confirm_tv.setOnClickListener {
            if (isShowing) {
                dismiss()
            }
            mListener?.onDelete()
        }

        delete_bill_dialog_cancel_tv.setOnClickListener {
            if (isShowing)
                dismiss()
        }
    }

}