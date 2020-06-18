package com.paulniu.billing.widget.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paulniu.bill_data_lib.bean.BillNoteBean
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.bill_data_lib.source.BillNoteSource
import com.paulniu.billing.R
import com.paulniu.billing.listener.IAddBillNoteListener
import kotlinx.android.synthetic.main.view_add_bill_note_item.view.*
import kotlinx.android.synthetic.main.view_dialog_add_bill_note.*


/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/18 3:44 PM
 * desc: 添加账单时账单备注弹窗
 */
class AddBillNoteDialog(context: Context) : Dialog(context) {

    private var mListener: IAddBillNoteListener? = null
    private var mTypeInfo: TypeInfo? = null
    private var mBillNoteList = ArrayList<BillNoteBean>()
    private var mCurrentBillNote: BillNoteBean? = null
    private var mAdapter: AddBillNoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_dialog_add_bill_note)

        // 设置弹窗样式
        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        add_bill_note_dialog_confirm_tv.setOnClickListener {
            if (isShowing) {
                dismiss()
            }
            val note = add_bill_note_dialog_content_et.text.trim().toString()
            if (!TextUtils.isEmpty(note)) {
                // 点击确定按钮，向数据库中添加备注，并返回数据
                if (null == mCurrentBillNote) {
                    // 新添加的对象
                    val billNote = BillNoteBean(0, note, mTypeInfo!!.id, mTypeInfo!!)
                    BillNoteSource.addBillNote(billNote)
                    mListener?.onAddBillNote(billNote)
                } else {
                    // 不是新添加的对象
                    mCurrentBillNote!!.count++
                    BillNoteSource.updateBillNote(mCurrentBillNote!!)
                    mListener?.onAddBillNote(mCurrentBillNote!!)
                }

            }
        }

        add_bill_note_dialog_cancel_tv.setOnClickListener {
            if (isShowing) {
                dismiss()
            }
        }

        initData()
    }

    override fun dismiss() {
        // 关闭软键盘
        val imm: InputMethodManager? =
            getSystemService(context, InputMethodManager::class.java)
        //如果window上view获取焦点 && view不为空
        //如果window上view获取焦点 && view不为空
        if (imm!!.isActive && currentFocus != null) {
            //拿到view的token 不为空
            if (currentFocus!!.windowToken != null) {
                //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(
                    currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
        super.dismiss()
    }

    /**
     * 从数据库中获取当前类型下的备注列表，并填充到列表中
     */
    private fun initData() {
        mBillNoteList.clear()
        mBillNoteList.addAll(BillNoteSource.getBillNotesByType(mTypeInfo!!.id) as ArrayList<BillNoteBean>)
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        add_bill_note_dialog_rv.layoutManager = linearLayout
        mAdapter = AddBillNoteAdapter(context, mBillNoteList)
        add_bill_note_dialog_rv.adapter = mAdapter
    }

    fun setAddBillNoteListener(listener: IAddBillNoteListener) {
        this.mListener = listener
    }

    fun setTypeInfo(typeInfo: TypeInfo) {
        this.mTypeInfo = typeInfo
    }

    inner class AddBillNoteAdapter(context: Context, billNoteList: ArrayList<BillNoteBean>) :
        RecyclerView.Adapter<AddBillNoteViewHolder>() {
        private var mContext: Context = context
        private var mBillNoteList: ArrayList<BillNoteBean> = billNoteList

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddBillNoteViewHolder {
            return AddBillNoteViewHolder(
                LayoutInflater.from(mContext)
                    .inflate(R.layout.view_add_bill_note_item, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return mBillNoteList.size
        }

        override fun onBindViewHolder(holder: AddBillNoteViewHolder, position: Int) {
            holder.addBillNoteItemContainer.setOnClickListener {
                mBillNoteList.forEach { billNote ->
                    billNote.isSelected = false
                }
                mBillNoteList[position].isSelected = true
                mAdapter?.notifyDataSetChanged()

                mCurrentBillNote = mBillNoteList[position]
                // 将输入框的内容显示为点击选择的文本
                add_bill_note_dialog_content_et.setText(mBillNoteList[position].title)
            }
            holder.addBillNoteItemTitle.text = mBillNoteList[position].title
            if (mBillNoteList[position].isSelected) {
                holder.addBillNoteItemTitle.background =
                    mContext.resources.getDrawable(R.drawable.shape_add_bill_note_title_select_bg)
            } else {
                holder.addBillNoteItemTitle.background =
                    mContext.resources.getDrawable(R.drawable.shape_add_bill_note_title_unselect_bg)
            }
        }

    }

    class AddBillNoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val addBillNoteItemContainer: ConstraintLayout = view.add_bill_note_item_container
        val addBillNoteItemTitle: TextView = view.add_bill_note_item_title_tv
    }

}