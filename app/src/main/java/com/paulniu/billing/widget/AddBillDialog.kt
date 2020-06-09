package com.paulniu.billing.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import com.paulniu.bill_data_lib.bean.BillInfo
import com.paulniu.billing.R
import kotlinx.android.synthetic.main.view_add_bill_dialog.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 9:15 PM
 * desc: 添加账单弹窗
 */
class AddBillDialog(context: Context, listenner: IAddBillListener) : Dialog(context) {

    private var mListener: IAddBillListener? = null

    init {
        mListener = listenner
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_add_bill_dialog)

        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 设置点击外部/返回键不消失
        setCancelable(false)
        setCanceledOnTouchOutside(false)

        add_bill_dialog_close_iv.setOnClickListener {
            dismiss()
        }
        btn_cancel.setOnClickListener {
            dismiss()
        }

        btn_add.setOnClickListener {
        }
    }

    interface IAddBillListener {
        fun onAddBill(billInfo: BillInfo)
    }

}