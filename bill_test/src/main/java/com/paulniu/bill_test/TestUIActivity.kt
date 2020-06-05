package com.paulniu.bill_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.paulniu.bill_data_lib.bean.BillBean
import com.paulniu.bill_data_lib.bean.TypeBean
import com.paulniu.bill_data_lib.source.TypeSource
import com.paulniu.billing.database.source.BillSource
import kotlinx.android.synthetic.main.activity_test_u_i.*

class TestUIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_u_i)

        btn01.setOnClickListener {
            var billBean = BillBean(
                null?.toLong(),
                "测试",
                12.9F,
                100L,
                System.currentTimeMillis()
            )
            billBean.type = TypeBean(100L, "测试")
            BillSource.addOrUpdateBill(billBean)
            TypeSource.addOrUpdateBillType(TypeBean(100L, "测试"))
        }

        btn02.setOnClickListener {
            val res = BillSource.queryByBillType(100L)
            Log.e("NPL", "输出结果是：${res[0].id}")
        }

    }
}
