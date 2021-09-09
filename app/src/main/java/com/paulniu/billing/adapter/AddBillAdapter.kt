package com.paulniu.billing.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.paulniu.bill_base_lib.util.ResourceUtil
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.billing.R
import com.paulniu.billing.listener.IAddBillSelectListener
import com.paulniu.billing.util.ResUtil
import kotlinx.android.synthetic.main.view_add_bill_item.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 9:18 PM
 * desc: 添加账单Adapter
 */
class AddBillAdapter(
    private var context: Context,
    types: ArrayList<TypeInfo>,
    listener: IAddBillSelectListener
) :
    RecyclerView.Adapter<AddBillAdapter.AddBillViewHolder>() {

    private var mTypes = ArrayList<TypeInfo>()
    private var mListener: IAddBillSelectListener? = null

    init {
        this.mTypes = types
        this.mListener = listener
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddBillViewHolder {
        return AddBillViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_add_bill_item, null, false)
        )
    }

    override fun getItemCount(): Int {
        return mTypes.size
    }

    override fun onBindViewHolder(holder: AddBillViewHolder, position: Int) {
        holder.addBillItemTitle.text = mTypes[position].title
        holder.addBillItemIcon.background =
            ResourceUtil.getDrawable(
                ResUtil.mipmapResource(
                    "app_icon_" + mTypes[position].iconRes,
                    context
                )
            )

        holder.addBillItemContainer.background =
            ResourceUtil.getDrawable(if (mTypes[position].isSelected) R.drawable.add_bill_item_selected_bg else R.drawable.add_bill_item_unselected_bg)

        holder.addBillItemContainer.setOnClickListener {
            // 点击选中之后，将所有的item重新设置数据
            mListener?.onSelected(position)
        }
    }

    class AddBillViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var addBillItemContainer: ConstraintLayout = view.view_add_bill_container
        var addBillItemIcon: ImageView = view.view_add_bill_item_icon_iv
        var addBillItemTitle: TextView = view.view_add_bill_item_title_tv
    }

}