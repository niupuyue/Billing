package com.paulniu.billing.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.paulniu.billing.R
import com.paulniu.billing.listener.ISoftKeyboardSelectListener
import kotlinx.android.synthetic.main.view_add_bill_money_button_item.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 2:47 PM
 * desc: 自定义软键盘Adapter
 */
class SoftKeyboardAdapter(context: Context, selectListener: ISoftKeyboardSelectListener) :
    RecyclerView.Adapter<SoftKeyboardAdapter.SoftKeyboardViewHolder>() {

    private var mContext: Context? = null
    private var mSelectListener: ISoftKeyboardSelectListener? = null

    private val mKeyboardList = ArrayList<KeyboardData>()

    init {
        mContext = context
        mSelectListener = selectListener
        initData()
    }

    private fun initData() {
        mKeyboardList.add(KeyboardData(0, "7"))
        mKeyboardList.add(KeyboardData(1, "8"))
        mKeyboardList.add(KeyboardData(2, "9"))
        // 此处按钮为保留按钮
        mKeyboardList.add(KeyboardData(3, "@"))

        mKeyboardList.add(KeyboardData(4, "4"))
        mKeyboardList.add(KeyboardData(5, "5"))
        mKeyboardList.add(KeyboardData(6, "6"))
        mKeyboardList.add(KeyboardData(7, "+"))

        mKeyboardList.add(KeyboardData(8, "1"))
        mKeyboardList.add(KeyboardData(9, "2"))
        mKeyboardList.add(KeyboardData(10, "3"))
        mKeyboardList.add(KeyboardData(11, "-"))

        mKeyboardList.add(KeyboardData(12, "."))
        mKeyboardList.add(KeyboardData(13, "0"))
        mKeyboardList.add(KeyboardData(14, "删除"))
        mKeyboardList.add(KeyboardData(15, "完成"))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoftKeyboardViewHolder {
        return SoftKeyboardViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.view_add_bill_money_button_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mKeyboardList.size
    }

    override fun onBindViewHolder(holder: SoftKeyboardViewHolder, position: Int) {
        val keyboardData = mKeyboardList[position]
        holder.softKeyboardItemTitl.text = keyboardData.value
        if (position == 15) {
            // 最后一个数据，改变背景颜色

        }
        holder.softKeyboardContainer.setOnClickListener {
            mSelectListener?.onSelectButton(keyboardData)
        }
    }

    class SoftKeyboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val softKeyboardContainer: ConstraintLayout = view.add_bill_item_container
        val softKeyboardItemTitl: TextView = view.add_bill_money_button_item_title
    }

    data class KeyboardData(
        var id: Int,
        var value: String
    )

}