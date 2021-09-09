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
import com.bumptech.glide.Glide
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.billing.R
import com.paulniu.billing.listener.IAnalysisItemListener
import com.paulniu.billing.util.ResUtil
import kotlinx.android.synthetic.main.view_analysis_item.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/13 5:35 PM
 * desc: 图表分析页  分类list项
 */
class AnalysisAdapter(context: Context, analysis: List<TypeInfo>, listener: IAnalysisItemListener) :
    RecyclerView.Adapter<AnalysisAdapter.AnalysisItemViewHolder>() {

    private var mContext: Context = context
    private var mAnalysis: List<TypeInfo>? = null
    private var mListener: IAnalysisItemListener? = null

    init {
        mAnalysis = analysis
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisItemViewHolder {
        return AnalysisItemViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.view_analysis_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (null == mAnalysis || mAnalysis?.isEmpty() == true) {
            0
        } else {
            mAnalysis?.size ?: 0
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AnalysisItemViewHolder, position: Int) {
        if (null != mAnalysis && mAnalysis?.isNotEmpty() == true) {
            val tempData = mAnalysis?.get(position)
            Glide.with(mContext)
                .load(ResUtil.mipmapResource("app_icon_" + tempData?.iconRes, mContext))
                .error(R.mipmap.app_icon_1)
                .into(holder.analysisItemIcon)
            holder.analysisItemTitle.text = tempData?.title
            holder.analysisItemPercent.text =
                "${String.format("%.2f", (tempData?.precent ?: 0f) * 100)}%"
            holder.analysisItemMoney.text = "￥ ${tempData?.totalMoney ?: 0f}"
            holder.analysisItemContainer.setOnClickListener {
                mListener?.onTypeItemClick(tempData)
            }
        }
        // 点击事件
        holder.analysisItemContainer.setOnClickListener {

        }
    }

    class AnalysisItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val analysisItemContainer: ConstraintLayout = view.analysis_item_view_container
        val analysisItemIcon: ImageView = view.analysis_item_view_icon_iv
        val analysisItemTitle: TextView = view.analysis_item_view_title_tv
        val analysisItemPercent: TextView = view.analysis_item_view_precent_tv
        val analysisItemMoney: TextView = view.analysis_item_view_money_tv
    }

}