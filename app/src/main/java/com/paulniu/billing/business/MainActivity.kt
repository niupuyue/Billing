package com.paulniu.billing.business

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.navigation.findNavController
import com.paulniu.bill_data_lib.bean.BillBean
import com.paulniu.billing.R
import com.paulniu.billing.database.source.BillSource
import com.paulniu.billing.widget.AddBillDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_activity_bar_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_top_menu, menu)
        return true
    }

    private fun initView() {
        setSupportActionBar(main_activity_toolbar)
    }

    private fun initListener() {
        // float按钮点击事件
        main_activity_fab.setOnClickListener {
            // 跳转到添加账单的页面

        }
    }


//    private val mAddBillDialog by lazy {
//        AddBillDialog(this, this)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        btn_add.setOnClickListener {
//            if (!mAddBillDialog.isShowing) {
//                mAddBillDialog.show()
//            }
//        }
//
//        btn_query.setOnClickListener {
//            val bills = BillSource.queryAllBill()
//            Toast.makeText(this, bills.toString(), Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onAddBill(billBean: BillBean) {
//        // 添加账单
//        val count = BillSource.addOrUpdateBill(billBean)
//        if (count > 0) {
//            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show()
//        }
//    }
}
