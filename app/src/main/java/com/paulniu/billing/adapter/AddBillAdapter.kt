package com.paulniu.billing.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.billing.R
import com.paulniu.billing.listener.IAddBillSelectListener
import kotlinx.android.synthetic.main.view_add_bill_item.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 9:18 PM
 * desc: 添加账单Adapter
 */
class AddBillAdapter(
    context: Context,
    types: ArrayList<TypeInfo>,
    listener: IAddBillSelectListener
) :
    RecyclerView.Adapter<AddBillAdapter.AddBillViewHolder>() {

    private var context: Context? = null
    private var mTypes = ArrayList<TypeInfo>()
    private var mListener: IAddBillSelectListener? = null

    init {
        this.context = context
        this.mTypes = types
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddBillViewHolder {
        return AddBillViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_add_bill_item, null, false)
        )
    }

    override fun getItemCount(): Int {
        return mTypes.size
    }

    override fun onBindViewHolder(holder: AddBillViewHolder, position: Int) {
        holder.add_bill_item_title.text = mTypes[position].title
        holder.add_bill_item_icon.background =
            ContextCompat.getDrawable(context!!, mTypes[position].iconRes!!)
        holder.add_bill_item_container.background = ContextCompat.getDrawable(
            context!!,
            if (mTypes[position].isSelected) R.drawable.add_bill_item_selected_bg else R.drawable.add_bill_item_unselected_bg
        )

        holder.add_bill_item_container.setOnClickListener {
            // 点击选中之后，将所有的item重新设置数据
            mListener?.onSelected(position)
        }
    }

    class AddBillViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var add_bill_item_container: ConstraintLayout = view.view_add_bill_container
        var add_bill_item_icon: ImageView = view.view_add_bill_item_icon_iv
        var add_bill_item_title: TextView = view.view_add_bill_item_title_tv
    }

}