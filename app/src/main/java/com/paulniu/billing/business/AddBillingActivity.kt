package com.paulniu.billing.business

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.paulniu.bill_base_lib.event.AddBillSuccessEvent
import com.paulniu.bill_data_lib.bean.BillInfo
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.bill_data_lib.source.TypeSource
import com.paulniu.billing.R
import com.paulniu.billing.adapter.AddBillAdapter
import com.paulniu.billing.database.BillSource
import com.paulniu.billing.listener.IAddBillSelectListener
import kotlinx.android.synthetic.main.activity_add_billing.*
import org.greenrobot.eventbus.EventBus

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 6:06 PM
 * desc: 添加账单页面
 */
class AddBillingActivity : AppCompatActivity(), IAddBillSelectListener {

    private var mAdapter: AddBillAdapter? = null
    private var mTypeDatas = ArrayList<TypeInfo>()

    // 选中的账单分类
    private var mSelectedType: TypeInfo? = null

    private var mCurrTime = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_billing)
        initData()
        initView()
        initListener()
    }

    override fun onSelected(position: Int) {
        mSelectedType = mTypeDatas[position]
        // 将数据中选中的状态更新
        for (type: TypeInfo in mTypeDatas) {
            type.isSelected = false
        }
        mTypeDatas[position].isSelected = true
        mAdapter?.notifyDataSetChanged()
        // 设置输入框中的内容也发生改变
        add_bill_activity_input_view.setBillTitle(mSelectedType?.iconRes, mSelectedType?.title)
    }

    private fun initData() {
        // 从数据库中获取数据
        mTypeDatas = TypeSource.queryTypeInfosByBaseType(1) as ArrayList<TypeInfo>
        // 将第一个默认为选中状态
        mTypeDatas[0].isSelected = true
        mSelectedType = mTypeDatas[0]
        // 设置输入框初始样式
        add_bill_activity_input_view.setBillTitle(mSelectedType?.iconRes, mSelectedType?.title)
    }

    private fun initListener() {
        add_bill_activity_back_iv.setOnClickListener {
            onBackPressed()
        }
        add_bill_activity_submit_tv.setOnClickListener {
            // 添加记账数据
            val bill = BillInfo(
                0,
                "标题",
                add_bill_activity_input_view.getBillMoney()!!.toFloat(),
                mSelectedType!!.id,
                mSelectedType,
                mCurrTime
            )
            BillSource.addOrUpdate(bill)
            Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show()
            // 通过EventBus通知账单页面刷新
            EventBus.getDefault().post(AddBillSuccessEvent())
            // 关闭软键盘

            // 退出当前页面
            finish()
        }
        add_bill_activity_time_tv.setOnClickListener {
            // 选择时间
        }
    }

    private fun initView() {
        mAdapter = AddBillAdapter(this, mTypeDatas, this)
        add_bill_activity_items_recyclerview.adapter = mAdapter
        add_bill_activity_items_recyclerview.layoutManager = GridLayoutManager(this, 5)
    }

}