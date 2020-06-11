package com.paulniu.billing.widget.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import com.paulniu.bill_base_lib.event.ChangeUserNameEvent
import com.paulniu.bill_base_lib.util.SPUtil
import com.paulniu.billing.Constant
import com.paulniu.billing.R
import com.paulniu.billing.listener.IEditNickNameAndMottoListener
import com.paulniu.billing.listener.IMainBillListDeleteListener
import kotlinx.android.synthetic.main.view_dialog_delete_bill.*
import kotlinx.android.synthetic.main.view_dialog_edit_nickname_motto.*
import org.greenrobot.eventbus.EventBus

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/11 6:17 PM
 * desc: 编辑昵称和座右铭的弹窗
 */
class EditNickNameAndMottoDialog(
    context: Context,
    type: Int,
    listener: IEditNickNameAndMottoListener
) : Dialog(context) {

    companion object {
        const val TYPE_NICKNAME = 0
        const val TYPE_MOTTO = 1
    }

    private var mListener: IEditNickNameAndMottoListener? = null
    private var mType: Int = TYPE_NICKNAME

    init {
        mListener = listener
        mType = type
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_dialog_edit_nickname_motto)

        // 设置弹窗样式
        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (mType == TYPE_NICKNAME) {
            edit_nickname_motto_dialog_title.text = String.format(
                context.getString(R.string.edit_nickname_motto_dialog_title),
                context.getString(R.string.edit_nickname_motto_dialog_nickname)
            )
            edit_nickname_motto_dialog_content_et.hint =
                context.getString(R.string.edit_nickname_motto_dialog_nickname_hint)
        } else {
            edit_nickname_motto_dialog_title.text = String.format(
                context.getString(R.string.edit_nickname_motto_dialog_title),
                context.getString(R.string.edit_nickname_motto_dialog_motto)
            )
            edit_nickname_motto_dialog_content_et.hint =
                context.getString(R.string.edit_nickname_motto_dialog_motto_hint)
        }

        edit_nickname_motto_dialog_confirm_tv.setOnClickListener {
            if (isShowing) {
                dismiss()
            }
            mListener?.onChange(mType, edit_nickname_motto_dialog_content_et.text.toString())
            SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)?.put(
                if (mType == TYPE_NICKNAME) Constant.SP_KEY_USER_BASE_NAME else Constant.SP_KEY_USER_MOTTO,
                edit_nickname_motto_dialog_content_et.text.toString()
            )
            EventBus.getDefault().postSticky(ChangeUserNameEvent())
        }

        edit_nickname_motto_dialog_cancel_tv.setOnClickListener {
            if (isShowing) {
                dismiss()
            }
        }

    }

}