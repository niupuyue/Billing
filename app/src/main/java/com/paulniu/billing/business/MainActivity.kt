package com.paulniu.billing.business

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.paulniu.bill_data_lib.bean.BillBean
import com.paulniu.billing.R
import com.paulniu.billing.database.source.BillSource
import com.paulniu.billing.widget.AddBillDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddBillDialog.IAddBillListener {

    private val mAddBillDialog by lazy {
        AddBillDialog(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener {
            if (!mAddBillDialog.isShowing) {
                mAddBillDialog.show()
            }
        }

        btn_query.setOnClickListener {
            val bills = BillSource.queryAllBill()
            Toast.makeText(this, bills.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAddBill(billBean: BillBean) {
        // 添加账单
        val count = BillSource.addOrUpdateBill(billBean)
        if (count > 0) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show()
        }
    }
}
