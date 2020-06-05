package com.paulniu.billing.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import com.paulniu.bill_data_lib.bean.BillBean
import com.paulniu.bill_data_lib.bean.TypeBean
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
            var bill = BillBean()
            bill.id = null?.toLong()
            bill.type = when {
                TextUtils.equals(add_bill_dialog_type_et.text, "100") -> TypeBean(
                    100L,
                    "吃饭"
                )
                TextUtils.equals(add_bill_dialog_type_et.text, "101") -> TypeBean(
                    101L,
                    "公交"
                )
                else -> TypeBean(99L, "其他")
            }
            bill.typeId = bill.type.id
            bill.money = add_bill_dialog_money_et.text.toString().toFloat()
            bill.title = add_bill_dialog_title_et.text.toString()
            bill.time = System.currentTimeMillis()
            mListener?.onAddBill(bill)
            dismiss()
        }
    }

    interface IAddBillListener {
        fun onAddBill(billBean: BillBean)
    }

}