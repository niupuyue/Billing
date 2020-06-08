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
import com.paulniu.bill_base_lib.util.TimeUtil
import com.paulniu.bill_data_lib.bean.BillBean
import com.paulniu.billing.R
import com.paulniu.billing.listener.IMainBillListListener
import kotlinx.android.synthetic.main.main_activity_bill_list_item.view.*
import kotlinx.android.synthetic.main.main_activity_bill_list_item_title.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/8 12:31 PM
 * desc: 首页中的账单列表
 */
class MainBillListAdapter(
    context: Context,
    bills: ArrayList<BillBean>,
    listener: IMainBillListListener
) : RecyclerView.Adapter<MainBillListAdapter.BillListViewHolder>() {

    private var mContext: Context? = null
    private var mBillList: ArrayList<BillBean>? = null
    private var mListener: IMainBillListListener? = null

    companion object {
        private const val TYPE_DATE = 0
        private const val TYPE_BILL = 1
    }

    init {
        this.mContext = context
        this.mBillList = bills
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillListViewHolder {
        return when (viewType) {
            TYPE_DATE -> {
                TitleBillListViewHolder(
                    LayoutInflater.from(mContext)
                        .inflate(R.layout.main_activity_bill_list_item_title, parent, false)
                )
            }
            TYPE_BILL -> {
                MainBillListViewHolder(
                    LayoutInflater.from(mContext)
                        .inflate(R.layout.main_activity_bill_list_item, parent, false)
                )
            }
            else -> {
                MainBillListViewHolder(
                    LayoutInflater.from(mContext)
                        .inflate(R.layout.main_activity_bill_list_item, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (null == mBillList || mBillList!!.isEmpty()) {
            0
        } else {
            mBillList!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mBillList!![position].id <= 0) {
            TYPE_DATE
        } else {
            TYPE_BILL
        }
    }

    override fun onBindViewHolder(holder: BillListViewHolder, position: Int) {
        if (holder is MainBillListViewHolder) {
            val mainBill = mBillList?.get(position)
            mainBill?.run {
                if (null != typeBean) {
                    holder.billListItemIcon.background =
                        ContextCompat.getDrawable(mContext!!, typeBean!!.iconRes)
                    holder.billListItemTitle.text = typeBean!!.title
                }
                holder.billListItemMoney.text = money.toString()
            }
            holder.billListItemContainer.setOnClickListener {
                mListener?.onClick(position)
            }
            holder.billListItemContainer.setOnLongClickListener {
                mListener?.onLongClick(position)
                true
            }
        } else if (holder is TitleBillListViewHolder) {
            val titleBill = mBillList?.get(position)
            titleBill?.run {
                holder.billListTitleDate.text = TimeUtil.formatTimeToMonthAndDay(time)
                holder.billListTitleWeek.text = TimeUtil.formatTimeToWeek(time)
            }
        }
    }

    class MainBillListViewHolder(view: View) : BillListViewHolder(view) {
        var billListItemContainer: ConstraintLayout = view.bill_list_item_container
        var billListItemIcon: ImageView = view.bill_list_item_icon_iv
        var billListItemTitle: TextView = view.bill_list_item_title_tv
        var billListItemMoney: TextView = view.bill_list_item_money_tv
    }

    class TitleBillListViewHolder(view: View) : BillListViewHolder(view) {
        var billListTitleDate: TextView = view.bill_list_item_title_date_tv
        var billListTitleWeek: TextView = view.bill_list_item_title_week_tv
        var billListTitleMoney: TextView = view.bill_list_item_title_money_tv
    }

    open class BillListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}